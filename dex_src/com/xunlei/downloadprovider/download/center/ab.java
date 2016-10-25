package com.xunlei.downloadprovider.download.center;

// compiled from: DownloadCenterActivityFragment.java
final class ab implements Runnable {
    final /* synthetic */ DownloadCenterActivityFragment a;

    ab(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void run() {
        if (this.a.isVisible()) {
            DownloadCenterActivityFragment.r(this.a).a(false, false);
            DownloadCenterActivityFragment.r(this.a).a();
            DownloadCenterActivityFragment.h(this.a);
        }
    }
}
