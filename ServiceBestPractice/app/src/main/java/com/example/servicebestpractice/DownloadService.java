package com.example.servicebestpractice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.io.File;

public class DownloadService extends Service {
    private final String CHANNEL_ID = "com.example.servicebestpractice";

    private DownloadTask downloadTask;

    private String downloadUrl;

    private final DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification(getString(R.string.downloading), progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification(getString(R.string.download_succeeded), -1));
            Toast.makeText(DownloadService.this, getString(R.string.download_succeeded), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification(getString(R.string.download_failed), -1));
            Toast.makeText(DownloadService.this, getString(R.string.download_failed), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, getString(R.string.download_paused), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, getString(R.string.download_canceled), Toast.LENGTH_SHORT).show();
        }
    };

    private final DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification(getString(R.string.downloading), 0), ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE);
                Toast.makeText(DownloadService.this, getString(R.string.downloading), Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            } else {
                if (downloadUrl != null) {
                    String filename = downloadUrl.substring(downloadUrl.lastIndexOf('/'));
                    String directory = Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + filename);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, getString(R.string.download_canceled), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String name = "Download";
        getNotificationManager().createNotificationChannel(
            new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
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
}