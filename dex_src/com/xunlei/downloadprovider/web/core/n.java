package com.xunlei.downloadprovider.web.core;

import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.url.DownData;

// compiled from: ThunderWebView.java
final class n implements c {
    final /* synthetic */ DownData a;
    final /* synthetic */ boolean b;
    final /* synthetic */ ThunderWebView c;

    n(ThunderWebView thunderWebView, DownData downData) {
        this.c = thunderWebView;
        this.a = downData;
        this.b = false;
    }

    public final void a(DownloadService downloadService) {
        if (downloadService != null) {
            this.c.f.a(this.a);
        }
    }
}
