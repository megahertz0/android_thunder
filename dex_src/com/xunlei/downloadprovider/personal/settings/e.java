package com.xunlei.downloadprovider.personal.settings;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: AboutBoxActivity.java
final class e implements OnClickListener {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        try {
            this.a.a.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:4001111000")));
        } catch (ActivityNotFoundException e) {
            XLToast.a(this.a.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u62e8\u53f7\u5931\u8d25!");
        }
        StatReporter.reportClick(5005, "call", c.f);
    }
}
