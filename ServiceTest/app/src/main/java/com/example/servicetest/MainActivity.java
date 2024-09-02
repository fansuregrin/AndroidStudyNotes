package com.example.servicetest;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private static final int REQUEST_PERMISSION_CODE = 1;

    private MyService.DownloadBinder downloadBinder;

    private TextView displayArea;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            downloadBinder = (MyService.DownloadBinder) service;

            downloadBinder.getService().setCallback(new MyService.Callback() {
                @Override
                public void displayMsg(String msg) {
                    displayArea.setText(msg);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayArea = findViewById(R.id.display_area);
        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);
        findViewById(R.id.btn_start_foreground_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_foreground_service).setOnClickListener(this);
        findViewById(R.id.btn_start_intent_service).setOnClickListener(this);
        findViewById(R.id.btn_start_download).setOnClickListener(this);
        findViewById(R.id.btn_pause_download).setOnClickListener(this);
        findViewById(R.id.btn_get_progress).setOnClickListener(this);

        // 检查权限
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS)
        != PackageManager.PERMISSION_GRANTED) {
            String [] permissions = { Manifest.permission.POST_NOTIFICATIONS };
            ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_PERMISSION_CODE);
        }

        Log.d(TAG, "onCreate");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: the user granted permission POST_NOTIFICATIONS");
            } else {
                Log.w(TAG, "onRequestPermissionsResult: the user denied permission POST_NOTIFICATIONS");
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_start_service) {
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        } else if (id == R.id.btn_stop_service) {
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
        } else if (id == R.id.btn_bind_service) {
            Intent bindIntent = new Intent(this, MyService.class);
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        } else if (id == R.id.btn_unbind_service) {
            unbindService(connection);
        } else if (id == R.id.btn_start_foreground_service) {
            Intent intent = new Intent(this, ForegroundService.class);
            startForegroundService(intent);
            Log.d(TAG, "onClick: start foreground service");
        } else if (id == R.id.btn_stop_foreground_service) {
            Intent intent = new Intent(this, ForegroundService.class);
            stopService(intent);
            Log.d(TAG, "onClick: stop foreground service");
        } else if (id == R.id.btn_start_intent_service) {
            Log.d(TAG, "onClick: thread id is " + Thread.currentThread().getId());
            Intent intent = new Intent(this, MyIntentService.class);
            startService(intent);
        } else if (id == R.id.btn_start_download) {
            if (downloadBinder != null) {
                downloadBinder.startDownload();
            }
        } else if (id == R.id.btn_pause_download) {
            if (downloadBinder != null) {
                downloadBinder.pauseDownload();
            }
        } else if (id == R.id.btn_get_progress) {
            if (downloadBinder != null) {
                downloadBinder.getProgress();
            }
        }
    }
}