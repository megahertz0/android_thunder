package com.xunlei.downloadprovider.web;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.task.d;

// compiled from: DetailPageBrowserActivity.java
final class o implements OnClickListener {
    final /* synthetic */ DetailPageBrowserActivity a;

    o(DetailPageBrowserActivity detailPageBrowserActivity) {
        this.a = detailPageBrowserActivity;
    }

    public final void onClick(View view) {
        if (this.a.e.p.a()) {
            d.a();
            d.o();
        }
        DownloadCenterActivity.a(this.a, DLCenterEntry.browser.toString());
        if ((this.a.n & 2048) == 2048) {
            StatReporter.reportDownloadEntryClick(LogBuilder.KEY_CHANNEL);
        } else if (this.a.n == 32) {
            StatReporter.reportDownloadEntryClick("btdigg");
        }
    }
}
