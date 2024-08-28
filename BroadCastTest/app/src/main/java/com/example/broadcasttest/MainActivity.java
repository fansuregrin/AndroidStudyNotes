package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private NetworkChangeReceiver networkChangeReceiver;

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);

            // 'getActiveNetworkInfo()' is deprecated as of API 29 ("Q"; Android 10.0)
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            // 'isAvailable()' is deprecated as of API 28 ("Pie"; Android 9.0)
            if (networkInfo != null && networkInfo.isAvailable()) {
                // 'getTypeName()' is deprecated as of API 28 ("Pie"; Android 9.0)
                Log.d(TAG, "onReceive: " + networkInfo.getTypeName() + " is available");
                Toast.makeText(context, R.string.net_available, Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "onReceive: network is unavailable");
                Toast.makeText(context, R.string.net_unavailable, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

        Button btnSendBroadcast = findViewById(R.id.btnSendBroadcast);
        btnSendBroadcast.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
}