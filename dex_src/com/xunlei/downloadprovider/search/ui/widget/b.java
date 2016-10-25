package com.xunlei.downloadprovider.search.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.task.d;

// compiled from: HomeTitleBar.java
final class b implements OnClickListener {
    final /* synthetic */ HomeTitleBar a;

    b(HomeTitleBar homeTitleBar) {
        this.a = homeTitleBar;
    }

    public final void onClick(View view) {
        if (this.a.b.a()) {
            d.a();
            d.o();
        }
        DownloadCenterActivity.a(this.a.getContext(), this.a.d.toString());
        StatReporter.reportDownloadEntryClick("home");
    }
}
