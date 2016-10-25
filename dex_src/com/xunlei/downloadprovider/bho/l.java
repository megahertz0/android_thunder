package com.xunlei.downloadprovider.bho;

import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;

// compiled from: ThunderTaskBHOActivity.java
final class l implements c {
    final /* synthetic */ ThunderTaskBHOActivity a;

    l(ThunderTaskBHOActivity thunderTaskBHOActivity) {
        this.a = thunderTaskBHOActivity;
    }

    public final void a(DownloadService downloadService) {
        this.a.e.sendEmptyMessage(1);
    }
}
