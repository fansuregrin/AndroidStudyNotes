package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AnotherBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "AnotherBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: received broadcast");
        Toast.makeText(context, R.string.received_my_broadcast, Toast.LENGTH_SHORT).show();
    }
}