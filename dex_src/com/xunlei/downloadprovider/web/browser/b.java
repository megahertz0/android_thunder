package com.xunlei.downloadprovider.web.browser;

import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.thundersniffer.sniff.SniffingResource;

// compiled from: BrowserActivity.java
final class b implements c {
    final /* synthetic */ SniffingResource a;
    final /* synthetic */ BrowserActivity b;

    b(BrowserActivity browserActivity, SniffingResource sniffingResource) {
        this.b = browserActivity;
        this.a = sniffingResource;
    }

    public final void a(DownloadService downloadService) {
        BrowserActivity.a(this.b, this.a);
        StatReporter.reportBrowserCollectSniffDownload();
    }
}
