package com.example.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startNormalActivity = findViewById(R.id.start_normal_activity);
        startNormalActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NormalActivity.class);
            startActivity(intent);
        });

        Button startDialogActivity = findViewById(R.id.start_dialog_activity);
        startDialogActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DialogActivity.class);
            startActivity(intent);
        });

        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}