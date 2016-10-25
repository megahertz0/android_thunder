package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.uc.addon.sdk.remote.DownloadTask;
import com.uc.addon.sdk.remote.DownloadTaskArg;
import com.uc.addon.sdk.remote.protocol.IDownloadTaskStatusChangeListener.Stub;

public abstract class DownloadTaskStatusChangeListener extends Stub {
    public static final int DOWNLOAD_SERVICE_DELETE_TASK = 4;
    public static final int DOWNLOAD_SERVICE_TASK_STATUS_CHANGE = 2;
    private Handler a;

    class AnonymousClass_1 implements Runnable {
        private /* synthetic */ int a;
        private /* synthetic */ DownloadTaskArg b;

        AnonymousClass_1(int i, DownloadTaskArg downloadTaskArg) {
            this.a = i;
            this.b = downloadTaskArg;
        }

        public void run() {
            DownloadTaskStatusChangeListener.this.onStatusChange(this.a, this.b.downloadTask);
        }
    }

    public DownloadTaskStatusChangeListener() {
        this.a = new Handler(Looper.getMainLooper());
    }

    public abstract void onStatusChange(int i, DownloadTask downloadTask);

    public void onStatusChangeRemote(int i, Bundle bundle) throws RemoteException {
        DownloadTaskArg downloadTaskArg = new DownloadTaskArg();
        downloadTaskArg.fromBundle(bundle);
        this.a.post(new AnonymousClass_1(i, downloadTaskArg));
    }
}
