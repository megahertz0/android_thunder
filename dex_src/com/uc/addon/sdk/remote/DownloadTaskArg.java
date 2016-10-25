package com.uc.addon.sdk.remote;

import android.os.Bundle;
import com.uc.addon.sdk.remote.protocol.BaseArg;

public class DownloadTaskArg implements BaseArg {
    public DownloadTask downloadTask;

    public boolean checkArgs() {
        return (this.downloadTask == null || this.downloadTask.url == null) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        bundle.setClassLoader(DownloadTask.class.getClassLoader());
        this.downloadTask = (DownloadTask) bundle.getParcelable("download_task_ex");
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable("download_task_ex", this.downloadTask);
    }
}
