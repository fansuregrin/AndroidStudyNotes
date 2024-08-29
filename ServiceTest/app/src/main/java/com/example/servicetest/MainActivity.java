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
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private static final int REQUEST_PERMISSION_CODE = 1;

    private MyService.DownloadBinder downloadBinder;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
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

        Button btnStartService = findViewById(R.id.btn_start_service);
        btnStartService.setOnClickListener(this);
        Button btnStopService = findViewById(R.id.btn_stop_service);
        btnStopService.setOnClickListener(this);

        Button btnBindService = findViewById(R.id.btn_bind_service);
        btnBindService.setOnClickListener(this);

        Button btnUnbindService = findViewById(R.id.btn_unbind_service);
        btnUnbindService.setOnClickListener(this);

        Button btnStartForegroundService = findViewById(R.id.btn_start_foreground_service);
        btnStartForegroundService.setOnClickListener(this);

        Button btnStopForegroundService = findViewById(R.id.btn_stop_foreground_service);
        btnStopForegroundService.setOnClickListener(this);

        Button btnStartIntentService = findViewById(R.id.btn_start_intent_service);
        btnStartIntentService.setOnClickListener(this);

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
        }
    }
}