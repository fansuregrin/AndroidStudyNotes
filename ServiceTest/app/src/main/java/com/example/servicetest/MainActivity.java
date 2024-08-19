package com.example.servicetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartService = findViewById(R.id.btn_start_service);
        btnStartService.setOnClickListener(this);
        Button btnStopService = findViewById(R.id.btn_stop_service);
        btnStopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_service) {
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        } else if (v.getId() == R.id.btn_stop_service) {
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
        }
    }
}