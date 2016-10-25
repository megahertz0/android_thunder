package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter$UpgradeAlert;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From;

// compiled from: Update.java
final class t implements OnClickListener {
    final /* synthetic */ c a;

    t(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        c.a(this.a, dialogInterface);
        ThunderReporter$UpgradeAlert.b(From.FORCE_UPDATE);
    }
}
