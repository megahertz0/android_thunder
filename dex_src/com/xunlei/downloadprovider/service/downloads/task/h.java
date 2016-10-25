package com.xunlei.downloadprovider.service.downloads.task;

import android.os.Handler;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;

// compiled from: DownloadTaskManger.java
public final class h implements c {
    final /* synthetic */ c a;
    final /* synthetic */ Handler b;
    final /* synthetic */ d c;

    public h(d dVar, c cVar, Handler handler) {
        this.c = dVar;
        this.a = cVar;
        this.b = handler;
    }

    public final void a(DownloadService downloadService) {
        DownloadService.a().a(this.a, this.b);
    }
}
