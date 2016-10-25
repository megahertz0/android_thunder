package com.uc.addon.sdk.remote;

import com.uc.addon.sdk.remote.protocol.DownloadTaskCreateResultListener;
import com.uc.addon.sdk.remote.protocol.DownloadTaskStatusChangeListener;
import com.uc.addon.sdk.remote.protocol.IDownloadTaskCreateResultListener;
import com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener;

public class DownloadTaskRequest {
    public static final int DOWNLOAD_TYPE_SINGLE_WEB_TASK = 0;
    public static final int DOWNLOAD_TYPE_TASK_VIDEO = 102;
    protected IDownloadTaskCreateResultListener a;
    public boolean ask;
    protected IDownloadTaskStatusChangeListener b;
    public String fileName;
    public String filePath;
    public String title;
    public int type;
    public String url;

    public DownloadTaskRequest() {
        this.ask = false;
        this.type = 0;
    }

    public IDownloadTaskCreateResultListener getDownloadTaskCreateResultListener() {
        return this.a;
    }

    public IDownloadTaskStatusChangeListener getDownloadTaskStatusChangeListener() {
        return this.b;
    }

    public void setDownloadTaskCreateResultListener(DownloadTaskCreateResultListener downloadTaskCreateResultListener) {
        this.a = downloadTaskCreateResultListener;
    }

    public void setDownloadTaskStatusChangeListener(DownloadTaskStatusChangeListener downloadTaskStatusChangeListener) {
        this.b = downloadTaskStatusChangeListener;
    }
}
