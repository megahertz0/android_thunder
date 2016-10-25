package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.service.downloads.task.k;
import java.util.Collection;

// compiled from: DownloadCenterActivityFragment.java
final class y implements k {
    int a;
    final /* synthetic */ DownloadCenterActivityFragment b;

    y(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.b = downloadCenterActivityFragment;
        this.a = 0;
    }

    public final void a(Collection<Long> collection) {
        if (DownloadCenterActivityFragment.k(this.b) != null) {
            DownloadCenterActivityFragment$c k = DownloadCenterActivityFragment.k(this.b);
            if (k.a != null) {
                k.a.a(collection);
            }
            DownloadCenterActivityFragment.k(this.b).b();
        }
    }

    public final void b(Collection<Long> collection) {
        this.a++;
        this.b.a(new z(this), 1000);
    }
}
