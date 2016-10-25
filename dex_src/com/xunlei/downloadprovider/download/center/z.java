package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.download.tasklist.a.h;

// compiled from: DownloadCenterActivityFragment.java
final class z implements Runnable {
    final /* synthetic */ y a;

    z(y yVar) {
        this.a = yVar;
    }

    public final void run() {
        if (this.a.a > 0) {
            this.a.a = 0;
            DownloadCenterActivityFragment.l(this.a.b);
            DownloadCenterActivityFragment.p(this.a.b);
            h.a().b(0);
        }
        DownloadCenterActivityFragment.l(this.a.b);
        DownloadCenterActivityFragment.p(this.a.b);
    }
}
