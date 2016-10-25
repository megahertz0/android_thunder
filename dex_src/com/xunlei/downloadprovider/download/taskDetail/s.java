package com.xunlei.downloadprovider.download.taskDetail;

import android.os.Message;
import com.xunlei.c.a.a.a;

// compiled from: DownloadCenterDetailFragment.java
final class s implements a {
    final /* synthetic */ DownloadCenterDetailFragment a;

    s(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void a(Message message) {
        if (message.what == 1) {
            DownloadCenterDetailFragment.v(this.a).dismiss();
        }
    }
}
