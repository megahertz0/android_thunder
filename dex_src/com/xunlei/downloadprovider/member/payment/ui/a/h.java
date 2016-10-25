package com.xunlei.downloadprovider.member.payment.ui.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: PayProblemAdapter.java
final class h implements OnClickListener {
    final /* synthetic */ c a;

    h(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        StatReporter.reportClick(5005, "call", "0");
    }
}
