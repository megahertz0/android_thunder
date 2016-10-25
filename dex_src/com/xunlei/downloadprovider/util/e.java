package com.xunlei.downloadprovider.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: CreateTaskFromClipBoardHandler.java
public final class e implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ d c;

    public e(d dVar, Context context, String str) {
        this.c = dVar;
        this.a = context;
        this.b = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        d.a(this.a, this.b);
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
        StatReporter.reportOverallDownload("paste_download");
    }
}
