package com.xunlei.downloadprovider.download.b;

import com.xunlei.downloadprovider.commonview.DownloadEntranceView;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: TaskMonitor.java
public final class a {
    DownloadEntranceView a;
    private a b;

    public a(DownloadEntranceView downloadEntranceView) {
        this.a = downloadEntranceView;
    }

    public final void a() {
        if (DownloadService.a() != null) {
            if (this.b == null) {
                this.b = new a(this);
            }
            a(false);
            d.a();
            d.a(this.b);
            DownloadService.a().b(this.b);
        }
    }

    public final void b() {
        if (this.b != null) {
            if (DownloadService.a() != null) {
                d.a();
                d.b(this.b);
                DownloadService.a().c(this.b);
            }
            this.b.removeCallbacksAndMessages(null);
        }
        DownloadEntranceView downloadEntranceView = this.a;
        downloadEntranceView.a.b.clearAnimation();
        downloadEntranceView.b = false;
    }

    public final void a(boolean z) {
        d.a();
        int k = d.k();
        if (k > 0) {
            a(k, z);
            return;
        }
        d.a();
        if (d.p()) {
            c();
            return;
        }
        DownloadEntranceView downloadEntranceView = this.a;
        if (downloadEntranceView.a != null) {
            com.xunlei.downloadprovider.commonview.DownloadEntranceView.a aVar = downloadEntranceView.a;
            aVar.b.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            aVar.c.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            aVar.d.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            aVar.e.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }

    final void a(int i, boolean z) {
        if (z) {
            this.a.setNumTextAnimate(i);
        } else {
            this.a.setNumText(i);
        }
        DownloadEntranceView downloadEntranceView = this.a;
        if (downloadEntranceView.a != null) {
            downloadEntranceView.a.e.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }

    final void c() {
        DownloadEntranceView downloadEntranceView = this.a;
        if (downloadEntranceView.a != null) {
            com.xunlei.downloadprovider.commonview.DownloadEntranceView.a aVar = downloadEntranceView.a;
            aVar.e.setVisibility(0);
            aVar.b.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            aVar.d.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            aVar.c.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }
}
