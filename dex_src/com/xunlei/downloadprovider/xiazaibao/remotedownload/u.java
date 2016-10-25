package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.xiazaibao.remotedownload.g.a;

// compiled from: RemoteDownloadListFragment.java
final class u implements a {
    final /* synthetic */ RemoteDownloadListFragment a;

    u(RemoteDownloadListFragment remoteDownloadListFragment) {
        this.a = remoteDownloadListFragment;
    }

    public final void a() {
        RemoteDownloadListFragment.a(this.a).set(true);
        RemoteDownloadListFragment.m(this.a).incrementAndGet();
    }

    public final void a(int i, boolean z) {
        new StringBuilder("\u64cd\u4f5c\u4efb\u52a1 taskType = ").append(i).append(" isSuccess = ").append(z);
        RemoteDownloadListFragment.a(this.a).set(false);
        RemoteDownloadListFragment.n(this.a);
    }
}
