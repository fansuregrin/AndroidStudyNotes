// DownloadListener.aidl
package com.example.downloadclient;

interface DownloadListener {
    void onStartDownload();
    void onProgress(int progress);
    void onSuccess();
    void onFail();
    void onPause();
    void onCancel();
}