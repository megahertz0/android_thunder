package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.c.a.b;

// compiled from: RemoteDownloadListFragment.java
final class r implements Runnable {
    final /* synthetic */ RemoteDownloadListFragment a;

    r(RemoteDownloadListFragment remoteDownloadListFragment) {
        this.a = remoteDownloadListFragment;
    }

    public final void run() {
        if (!this.a.d) {
            boolean z;
            RemoteDownloadListFragment remoteDownloadListFragment = this.a;
            if (remoteDownloadListFragment.c == null) {
                z = false;
            } else {
                z = b.a(remoteDownloadListFragment.c);
            }
            if (z && this.a.e && !RemoteDownloadListFragment.a(this.a).get() && RemoteDownloadListFragment.b(this.a)) {
                if (RemoteDownloadListFragment.d(this.a) == 2) {
                    RemoteDownloadListFragment.e(this.a);
                    return;
                } else {
                    RemoteDownloadListFragment.f(this.a);
                    return;
                }
            }
        }
        RemoteDownloadListFragment.c(this.a).postDelayed(this, (long) this.a.b());
    }
}
