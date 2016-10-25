package com.xunlei.downloadprovider.discovery.kuainiao.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: KuaiNiaoAccelerator.java
final class e implements OnClickListener {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        StatReporter.reportKuaiNiaoDialog("cancle");
    }
}
