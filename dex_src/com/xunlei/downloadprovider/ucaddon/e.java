package com.xunlei.downloadprovider.ucaddon;

import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;

// compiled from: UcAddonEventRecevier.java
final class e implements c {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ UcAddonEventRecevier d;

    e(UcAddonEventRecevier ucAddonEventRecevier, String str, String str2, String str3) {
        this.d = ucAddonEventRecevier;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final void a(DownloadService downloadService) {
        this.d.onDonwloadServicePrepared(this.a, this.b, this.c);
    }
}
