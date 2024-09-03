package com.example.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RemoteService extends Service {
    private static final String TAG = "RemoteService";

    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
        @Override
        public int getPid() {
            return android.os.Process.myPid();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) {
            Log.d(TAG, "basicTypes: anInt:" + anInt + " aLog:" + aLong + " aBoolean:"
            + aBoolean + " aFloat:" + aFloat + " aDouble:" + aDouble + " aString:" + aString);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}