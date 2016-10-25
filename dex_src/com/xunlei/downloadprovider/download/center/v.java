package com.xunlei.downloadprovider.download.center;

// compiled from: DownloadCenterActivityFragment.java
final class v implements Runnable {
    final /* synthetic */ u a;

    v(u uVar) {
        this.a = uVar;
    }

    public final void run() {
        if (this.a.a.isVisible()) {
            DownloadCenterActivityFragment.l(this.a.a);
            DownloadCenterActivityFragment.m(this.a.a);
        }
    }
}
