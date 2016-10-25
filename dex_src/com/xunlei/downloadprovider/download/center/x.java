package com.xunlei.downloadprovider.download.center;

// compiled from: DownloadCenterActivityFragment.java
final class x implements Runnable {
    final /* synthetic */ u a;

    x(u uVar) {
        this.a = uVar;
    }

    public final void run() {
        if (this.a.a.isVisible()) {
            DownloadCenterActivityFragment.l(this.a.a);
            DownloadCenterActivityFragment.m(this.a.a);
            DownloadCenterActivityFragment.o(this.a.a);
        }
    }
}
