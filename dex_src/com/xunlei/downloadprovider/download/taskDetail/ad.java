package com.xunlei.downloadprovider.download.taskDetail;

import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

// compiled from: DownloadTaskDetailBtListFragment.java
final class ad implements Callback {
    final /* synthetic */ DownloadTaskDetailBtListFragment a;

    ad(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment) {
        this.a = downloadTaskDetailBtListFragment;
    }

    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
        return false;
    }
}
