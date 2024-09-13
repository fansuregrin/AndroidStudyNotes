package com.example.downloadserver;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.io.File;

import com.example.downloadclient.DownloadListener;

public class DownloadService extends Service {
    private static final String TAG = "DownloadService";

    private DownloadTask downloadTask;

    private String downloadUrl;

    private DownloadListener listener = null;

    private final DownloadInterface.Stub mBinder = new DownloadInterface.Stub() {
        @Override
        public void startDownload(String url) throws RemoteException {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                listener.onStartDownload();
                Log.d(TAG, "startDownload: start download");
            }
        }

        @Override
        public void pauseDownload() throws RemoteException {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
                downloadTask = null;
                Log.d(TAG, "pauseDownload: pause download");
            }
        }

        @Override
        public void cancelDownload() throws RemoteException {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
                downloadTask = null;
            } else {
                listener.onCancel();
                if (downloadUrl != null) {
                    String filename = downloadUrl.substring(downloadUrl.lastIndexOf('/'));
                    String directory = Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + filename);
                    if (file.exists()) {
                        if (file.delete()) {
                            Log.d(TAG, "cancelDownload: delete the downloaded file successfully");
                        } else {
                            Log.d(TAG, "cancelDownload: failed to delete the downloaded file");
                        }
                    }
                }
            }
        }

        @Override
        public void setListener(DownloadListener listener) throws RemoteException {
            DownloadService.this.listener = listener;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}