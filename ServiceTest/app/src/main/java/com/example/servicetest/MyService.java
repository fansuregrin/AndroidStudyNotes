package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    private DownloadBinder mBinder = new DownloadBinder();

    private Callback callback;

    public interface Callback {
        void displayMsg(String msg);
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d(TAG, "startDownload");
            if (callback != null) {
                callback.displayMsg(getString(R.string.download_started));
            }
        }

        public int getProgress() {
            Log.d(TAG, "getProgress");
            if (callback != null) {
                callback.displayMsg(getString(R.string.get_progress));
            }
            return 0;
        }

        public void pauseDownload() {
            Log.d(TAG, "pauseDownload");
            if (callback != null) {
                callback.displayMsg(getString(R.string.download_paused));
            }
        }

        public MyService getService() {
            return MyService.this;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}