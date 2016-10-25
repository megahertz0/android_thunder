package com.xunlei.downloadprovider.service;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.service.DownloadService.c;
import java.util.Iterator;

// compiled from: DownloadService.java
final class e implements a {
    final /* synthetic */ DownloadService a;

    e(DownloadService downloadService) {
        this.a = downloadService;
    }

    public final void a(Message message) {
        DownloadService.k();
        if (DownloadService.i != null) {
            DownloadService.i.a(DownloadService.h);
            DownloadService.i = null;
        }
        Iterator it = DownloadService.j.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(DownloadService.h);
        }
        DownloadService.j.clear();
    }
}
