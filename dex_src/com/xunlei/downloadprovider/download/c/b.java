package com.xunlei.downloadprovider.download.c;

import com.xunlei.downloadprovider.service.downloads.task.d;

// compiled from: SpeedLimitHelper.java
final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        d.a().c(-1);
        this.a.a = this.a.b;
    }
}
