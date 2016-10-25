package com.xunlei.downloadprovider.download.taskDetail;

import android.os.Handler;
import android.os.Message;

// compiled from: DownloadTaskDetailBtListFragment.java
final class ac extends Handler {
    final /* synthetic */ DownloadTaskDetailBtListFragment a;

    ac(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment) {
        this.a = downloadTaskDetailBtListFragment;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            DownloadTaskDetailBtListFragment.a(this.a, this.a.t, this.a.x);
            if (this.a.f) {
                this.a.B.removeMessages(0);
                this.a.B.sendEmptyMessageDelayed(0, 2000);
            }
        }
    }
}
