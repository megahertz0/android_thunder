package com.uc.addon.sdk.remote;

import android.webkit.ValueCallback;
import com.uc.addon.sdk.remote.protocol.DownloadTaskStatusChangeListener;

public interface Download {
    void addTask(DownloadTaskRequest downloadTaskRequest);

    void cancleTask(int i);

    void pauseTask(int i);

    void queryTask(int i, ValueCallback valueCallback);

    void registerStatusChangeListener(int i, DownloadTaskStatusChangeListener downloadTaskStatusChangeListener);

    void restartTask(int i);

    void startTask(int i);
}
