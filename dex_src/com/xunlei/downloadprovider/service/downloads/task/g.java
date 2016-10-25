package com.xunlei.downloadprovider.service.downloads.task;

import com.xunlei.downloadprovider.service.downloads.kernel.c;
import com.xunlei.downloadprovider.service.downloads.task.a.k.a;

// compiled from: DownloadTaskManger.java
final class g extends a<Long> {
    final /* synthetic */ d a;

    g(d dVar, Long l) {
        this.a = dVar;
        super(l);
    }

    public final /* synthetic */ void a(Object obj) {
        Long l = (Long) obj;
        c a = c.a();
        long longValue = l.longValue();
        if (longValue < 0) {
            longValue = -1;
        }
        if (a.a != null) {
            a.a.setSpeedLimit(longValue, -1);
        }
        new StringBuilder("DownloadSDK: set DownloadSpeedLimit = ").append(longValue).append("KB/s");
    }
}
