package com.xunlei.downloadprovider.search.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.task.d;

// compiled from: HomeTopView.java
final class c implements OnClickListener {
    final /* synthetic */ HomeTopView a;

    c(HomeTopView homeTopView) {
        this.a = homeTopView;
    }

    public final void onClick(View view) {
        if (this.a.a.a()) {
            d.a();
            d.o();
        }
        DownloadCenterActivity.a(this.a.getContext(), this.a.e.toString());
        StatReporter.reportDownloadEntryClick("home");
    }
}
