package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter$UpgradeAlert;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From;

// compiled from: Update.java
final class v implements OnClickListener {
    final /* synthetic */ c a;

    v(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        From from;
        c.a(this.a, dialogInterface);
        if (c.b(this.a)) {
            from = From.CONFIG_UPDATE;
        } else {
            from = From.REC_ALERT;
        }
        ThunderReporter$UpgradeAlert.b(from);
    }
}
