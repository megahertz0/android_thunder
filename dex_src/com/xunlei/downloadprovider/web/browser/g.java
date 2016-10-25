package com.xunlei.downloadprovider.web.browser;

import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.url.DownData;

// compiled from: BrowserActivity.java
final class g implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ e e;

    g(e eVar, String str, String str2, String str3, String str4) {
        this.e = eVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public final void run() {
        this.e.a.b(new DownData(this.a, this.b, this.c), "browser/browser");
        StatReporter.reportOverallDownload(this.d);
    }
}
