package com.xunlei.downloadprovider.web.browser;

import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.url.DownData;

// compiled from: BrowserActivity.java
final class m implements c {
    final /* synthetic */ DownData a;
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ BrowserActivity d;

    m(BrowserActivity browserActivity, DownData downData, String str) {
        this.d = browserActivity;
        this.a = downData;
        this.b = false;
        this.c = str;
    }

    public final void a(DownloadService downloadService) {
        this.d.a(this.a, this.c);
    }
}
