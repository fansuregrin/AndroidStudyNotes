package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate " + this.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Log.d(TAG, "Task id is " + getTaskId());
        Log.d(TAG, "Process id " + Process.myPid());
        Log.d(TAG, "Thread id is " + Process.myTid());

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        });
    }
}