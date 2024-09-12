package com.example.downloadserver;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import java.io.File;

public class DownloadService extends Service {
    private DownloadTask downloadTask;

    private String downloadUrl;

    private final DownloadListener listener = null;

    private final DownloadInterface.Stub mBinder = new DownloadInterface.Stub() {
        @Override
        public void startDownload(String url) throws RemoteException {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                Toast.makeText(DownloadService.this, getString(R.string.downloading), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void pauseDownload() throws RemoteException {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        @Override
        public void cancelDownload() throws RemoteException {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            } else {
                if (downloadUrl != null) {
                    String filename = downloadUrl.substring(downloadUrl.lastIndexOf('/'));
                    String directory = Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + filename);
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
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