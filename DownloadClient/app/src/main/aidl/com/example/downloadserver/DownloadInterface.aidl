// DownloadInterface.aidl
package com.example.downloadserver;

// Declare any non-default types here with import statements

interface DownloadInterface {
    void startDownload(String url);
    void pauseDownload();
    void cancelDownload();
}