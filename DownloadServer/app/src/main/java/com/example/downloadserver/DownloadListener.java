package com.example.downloadserver;

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFail();
    void onPause();
    void onCancel();
}