package com.xunlei.downloadprovider.download.center;

// compiled from: DownloadCenterActivityFragment.java
final class w implements Runnable {
    final /* synthetic */ u a;

    w(u uVar) {
        this.a = uVar;
    }

    public final void run() {
        if (this.a.a.isVisible()) {
            DownloadCenterActivityFragment.n(this.a.a);
            DownloadCenterActivityFragment.m(this.a.a);
            DownloadCenterActivityFragment.l(this.a.a);
            return;
        }
        DownloadCenterActivityFragment.l(this.a.a);
    }
}
