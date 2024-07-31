package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d(TAG, data);
    }
}