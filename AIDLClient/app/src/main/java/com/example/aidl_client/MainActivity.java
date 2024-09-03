package com.example.aidl_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aidl_demo.IRemoteService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private IRemoteService iRemoteService;

    private Button mBtnServ;

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            iRemoteService = IRemoteService.Stub.asInterface(service);
            try {
                int remotePid = iRemoteService.getPid();
                int currentPid = Process.myPid();
                Log.d(TAG, "current pid: " + currentPid + ", remote pid: " + remotePid);
                iRemoteService.basicTypes(1, 123456789, true, 3.14f,
                        0.1235, "Hello?");
            } catch (RemoteException e) {
                Log.e(TAG, "onServiceConnected: ", e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            iRemoteService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnServ = findViewById(R.id.btnServ);
        mBtnServ.setOnClickListener(v -> {
            String btnText = mBtnServ.getText().toString();
            if (btnText.equals(getString(R.string.bind_serv))) {
                Intent intent = new Intent("com.example.aidl_demo.REMOTE_SERVICE");
                intent.setPackage("com.example.aidl_demo");
                bindService(intent, mConnection, BIND_AUTO_CREATE);
                mBtnServ.setText(R.string.unbind_serv);
            } else if (btnText.equals(getString(R.string.unbind_serv))) {
                unbindService(mConnection);
                mBtnServ.setText(R.string.bind_serv);
            }
        });
    }
}