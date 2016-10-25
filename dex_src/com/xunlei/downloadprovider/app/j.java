package com.xunlei.downloadprovider.app;

import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.tdlive.R;

// compiled from: ExternBroadcastReceiver.java
final class j implements c {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ExternBroadcastReceiver c;

    j(ExternBroadcastReceiver externBroadcastReceiver, String str, String str2) {
        this.c = externBroadcastReceiver;
        this.a = str;
        this.b = str2;
    }

    public final void a(DownloadService downloadService) {
        DownloadService downloadService2 = downloadService;
        downloadService2.a(this.a, this.b, (int) R.styleable.Toolbar_titleTextColor, "BHO/BHO", BrothersApplication.a().e);
    }
}
