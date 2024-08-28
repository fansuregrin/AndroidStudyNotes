package com.example.broadcasttest2;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private AnotherBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter("com.example.broadcasttest.MY_BROADCAST");
        broadcastReceiver = new AnotherBroadcastReceiver();
        registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}