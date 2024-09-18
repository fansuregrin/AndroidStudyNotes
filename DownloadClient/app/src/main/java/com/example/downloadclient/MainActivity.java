package com.example.downloadclient;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.downloadserver.DownloadInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String CHANNEL_ID = "com.example.downloadclient";
    private final int NOTIFY_ID = 1;

    private static final String TAG = "MainActivity";

    private DownloadInterface downloadBinder;

    private ProgressBar progressBar;

    private final DownloadListener.Stub listener = new DownloadListener.Stub() {
        @Override
        public void onStartDownload() throws RemoteException {
            getNotificationManager().notify(NOTIFY_ID,
                getNotification(getString(R.string.downloading), 0));
            Log.d(TAG, "onStartDownload");
        }

        @Override
        public void onProgress(int progress) throws RemoteException {
            progressBar.setProgress(progress, true);
            getNotificationManager().notify(NOTIFY_ID,
                getNotification(getString(R.string.downloading), progress));
            Log.d(TAG, "onProgress: download " + progress + "%...");
        }

        @Override
        public void onSuccess() throws RemoteException {
            getNotificationManager().notify(NOTIFY_ID,
                getNotification(getString(R.string.download_succeeded), -1));
            Log.d(TAG, "onSuccess: download succeeded");
        }

        @Override
        public void onFail() throws RemoteException {
            getNotificationManager().notify(NOTIFY_ID,
                getNotification(getString(R.string.download_failed), -1));
            Log.d(TAG, "onFail: download failed");
        }

        @Override
        public void onPause() throws RemoteException {
            int progress = progressBar.getProgress();
            getNotificationManager().notify(NOTIFY_ID,
                getNotification(getString(R.string.download_paused), progress));
            Log.d(TAG, "onPause: download task paused, current progress " + progress + "%");
        }

        @Override
        public void onCancel() throws RemoteException {
            progressBar.setProgress(0, true);
            getNotificationManager().cancel(NOTIFY_ID);
            Log.d(TAG, "onCancel: download task canceled");
        }
    };

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = DownloadInterface.Stub.asInterface(service);
            try {
                downloadBinder.setListener(listener);
            } catch (RemoteException e) {
                Log.e(TAG, "onServiceConnected: ", e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };

    private EditText downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        downloadUrl = findViewById(R.id.download_url);

        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.btn_start_download).setOnClickListener(this);
        findViewById(R.id.btn_pause_download).setOnClickListener(this);
        findViewById(R.id.btn_cancel_download).setOnClickListener(this);

        Intent intent = new Intent("com.example.downloadserver.DOWNLOAD_SERVICE");
        intent.setPackage("com.example.downloadserver");
        bindService(intent, connection, BIND_AUTO_CREATE);

        if (ContextCompat.checkSelfPermission(
            this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(MainActivity.this, new
                String[] {Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.INTERNET}, 1);
        }

        String name = "DownloadClient";
        getNotificationManager().createNotificationChannel(
            new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.permission_alert, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private NotificationManager getNotificationManager() {
        return getSystemService(NotificationManager.class);
    }

    private Notification getNotification(String title, int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    @Override
    public void onClick(View v) {
        if (downloadBinder == null) {
            return;
        }
        int id = v.getId();
        if (id == R.id.btn_start_download) {
            String url = downloadUrl.getText().toString().strip();
            try {
                downloadBinder.startDownload(url);
            } catch (RemoteException e) {
                Log.e(TAG, "onClick: ", e);
            }
        } else if (id == R.id.btn_pause_download) {
            try {
                downloadBinder.pauseDownload();
            } catch (RemoteException e) {
                Log.e(TAG, "onClick: ", e);
            }
        } else if (id == R.id.btn_cancel_download) {
            try {
                downloadBinder.cancelDownload();
            } catch (RemoteException e) {
                Log.e(TAG, "onClick: ", e);
            }
        }
    }
}