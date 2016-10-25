package com.xunlei.downloadprovider.member.payment.ui.a;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: PayProblemAdapter.java
final class i implements OnClickListener {
    final /* synthetic */ c a;

    i(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        try {
            this.a.a.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(this.a.a.getString(R.string.pay_problem_phone))));
        } catch (ActivityNotFoundException e) {
            XLToast.a(this.a.a, XLToastType.XLTOAST_TYPE_ALARM, this.a.a.getString(R.string.pay_problem_phone_fail));
        }
        StatReporter.reportClick(5005, "call", c.f);
    }
}
