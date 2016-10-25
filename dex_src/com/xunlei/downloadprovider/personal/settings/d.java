package com.xunlei.downloadprovider.personal.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: AboutBoxActivity.java
final class d implements OnClickListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        StatReporter.reportClick(5005, "call", "0");
    }
}
