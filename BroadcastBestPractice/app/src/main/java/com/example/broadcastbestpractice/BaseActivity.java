package com.example.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate " + getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy " + getClass().getSimpleName());
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter, RECEIVER_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    static class ForceOfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            new AlertDialog.Builder(context)
                .setTitle(R.string.warning)
                .setMessage(R.string.offline_msg)
                .setCancelable(true)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    ActivityCollector.finishAll();
                    Intent intent1 = new Intent(context, LoginActivity.class);
                    context.startActivity(intent1);
                })
                .show();
        }
    }
}
