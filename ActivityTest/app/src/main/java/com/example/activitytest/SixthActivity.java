package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SixthActivity extends AppCompatActivity {
    private static final String TAG = "SixthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate " + this.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sixth_layout);

        Log.d(TAG, "Task id is " + getTaskId());

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(this, SixthActivity.class);
            startActivity(intent);
        });

        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        });
    }
}