package com.xunlei.downloadprovider.discovery.remoteDownload;

import android.os.Handler;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.url.DownData;

// compiled from: RemoteDownloadActivity.java
final class g implements c {
    final /* synthetic */ DownData a;
    final /* synthetic */ Handler b;
    final /* synthetic */ com.xunlei.downloadprovider.model.g c;
    final /* synthetic */ boolean d;
    final /* synthetic */ a e;

    g(a aVar, DownData downData, com.xunlei.downloadprovider.model.g gVar) {
        this.e = aVar;
        this.a = downData;
        this.b = null;
        this.c = gVar;
        this.d = false;
    }

    public final void a(DownloadService downloadService) {
        if (downloadService != null) {
            this.e.a.createTask(this.a, this.b, this.c, this.d);
        }
    }
}
