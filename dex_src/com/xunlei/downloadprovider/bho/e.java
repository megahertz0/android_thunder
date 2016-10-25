package com.xunlei.downloadprovider.bho;

import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;

// compiled from: ScanCodeResultActivity.java
final class e implements c {
    final /* synthetic */ ScanCodeResultActivity a;

    e(ScanCodeResultActivity scanCodeResultActivity) {
        this.a = scanCodeResultActivity;
    }

    public final void a(DownloadService downloadService) {
        if (this.a.C) {
            BrowserUtil.a();
            BrowserUtil.a(this.a, this.a.p, true, StartFromType.outside);
            this.a.C = false;
            this.a.finish();
        } else if (this.a.r != null && !this.a.w && !this.a.B && this.a.i) {
            g gVar = new g(4, this.a.p, null);
            gVar.d = "BHO/BHO";
            if (this.a.x) {
                this.a.createLocalTask(this.a.p, this.a.r, 0, null, null, null, 1, gVar, this.a.G, false);
            } else {
                this.a.createLocalTask(this.a.p, this.a.r, 0, null, null, null, 1, gVar, null, false);
            }
            StatReporter.reportOverallDownload("BHO");
        }
    }
}
