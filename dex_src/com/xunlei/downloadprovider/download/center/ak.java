package com.xunlei.downloadprovider.download.center;

// compiled from: DownloadCenterActivityFragment.java
final class ak implements Runnable {
    final /* synthetic */ DownloadCenterActivityFragment$c a;

    ak(DownloadCenterActivityFragment$c downloadCenterActivityFragment$c) {
        this.a = downloadCenterActivityFragment$c;
    }

    public final void run() {
        do {
            this.a.c = 1;
            if (this.a.a != null) {
                this.a.a.c();
            }
        } while (this.a.c > 1);
    }
}
