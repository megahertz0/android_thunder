package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: DownloadCreateMoreTaskDialog.java
final class p implements OnClickListener {
    final /* synthetic */ l a;

    p(l lVar) {
        this.a = lVar;
    }

    public final void onClick(View view) {
        StatReporter.reportClick(17207, null, "bt");
        l.c(this.a);
        this.a.dismiss();
    }
}
