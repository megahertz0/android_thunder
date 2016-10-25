package com.xunlei.downloadprovider.frame;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: MainTabActivity.java
final class v implements OnClickListener {
    final /* synthetic */ MainTabActivity a;

    v(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        DownloadCenterActivity.a(this.a, DLCenterEntry.home.toString());
        StatReporter.reportNoNetWorkTipChoice("goComplete");
    }
}
