package com.example.servicebestpractice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DownloadService.DownloadBinder downloadBinder;

    private ProgressBar progressBar;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
            downloadBinder.setCallback(new DownloadService.Callback() {
                @Override
                public void setProgress(int progress) {
                    progressBar.setProgress(progress);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };

    private EditText downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadUrl = findViewById(R.id.download_url);

        progressBar = findViewById(R.id.progress_bar);

        Button btnStartDownload = findViewById(R.id.btn_start_download);
        Button btnPauseDownload = findViewById(R.id.btn_pause_download);
        Button btnCancelDownload = findViewById(R.id.btn_cancel_download);
        btnStartDownload.setOnClickListener(this);
        btnPauseDownload.setOnClickListener(this);
        btnCancelDownload.setOnClickListener(this);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);

        if (ContextCompat.checkSelfPermission(
            this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(MainActivity.this, new
                String[] {Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.INTERNET}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "拒绝权限将无法使用应用", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (downloadBinder == null) {
            return;
        }
        int id = v.getId();
        if (id == R.id.btn_start_download) {
            String url = downloadUrl.getText().toString().strip();
            downloadBinder.startDownload(url);
        } else if (id == R.id.btn_pause_download) {
            downloadBinder.pauseDownload();
        } else if (id == R.id.btn_cancel_download) {
            downloadBinder.cancelDownload();
        }
    }
}