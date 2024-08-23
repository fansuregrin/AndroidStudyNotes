package com.example.servicebestpractice;

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFail();
    void onPause();
    void onCancel();
}
