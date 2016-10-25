package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.download.center.widget.af;

// compiled from: DownloadCenterActivity.java
final class a implements Runnable {
    final /* synthetic */ DownloadCenterActivity a;

    a(DownloadCenterActivity downloadCenterActivity) {
        this.a = downloadCenterActivity;
    }

    public final void run() {
        if (DownloadCenterActivity.a(this.a) == null) {
            DownloadCenterActivity.a(this.a, new af(this.a));
            DownloadCenterActivity.a(this.a).setOnDismissListener(new b(this));
        }
        DownloadCenterActivity.a(this.a).a(DownloadCenterActivity.b(this.a), DownloadCenterActivity.c(this.a));
    }
}
