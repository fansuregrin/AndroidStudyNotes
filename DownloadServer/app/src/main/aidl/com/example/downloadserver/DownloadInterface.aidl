// DownloadInterface.aidl
package com.example.downloadserver;

import com.example.downloadclient.DownloadListener;

interface DownloadInterface {
    void startDownload(String url);
    void pauseDownload();
    void cancelDownload();
    void setListener(DownloadListener listener);
}