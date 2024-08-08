package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    private static final String TAG = "FourthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_layout);

        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        if (data != null) {
            Log.d(TAG, data);
        }
    }
}