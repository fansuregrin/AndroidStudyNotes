// DownloadListener.aidl
package com.example.downloadclient;

// Declare any non-default types here with import statements

interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFail();
    void onPause();
    void onCancel();
}