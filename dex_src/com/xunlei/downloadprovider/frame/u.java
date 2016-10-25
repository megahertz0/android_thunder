package com.xunlei.downloadprovider.frame;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: MainTabActivity.java
final class u implements OnClickListener {
    final /* synthetic */ MainTabActivity a;

    u(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        StatReporter.reportNoNetWorkTipChoice("ignore");
    }
}
