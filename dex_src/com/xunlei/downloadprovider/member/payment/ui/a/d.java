package com.xunlei.downloadprovider.member.payment.ui.a;

import android.view.View;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.c;

// compiled from: PayProblemAdapter.java
final class d extends c {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void onClick(View view) {
        c cVar = this.a;
        XLAlarmDialog xLAlarmDialog = new XLAlarmDialog(cVar.a);
        xLAlarmDialog.setTitle(cVar.a.getResources().getString(R.string.pay_auto_renew_title));
        String a = PayUtil.a(j.a().f());
        xLAlarmDialog.setContent(cVar.a.getResources().getString(R.string.pay_auto_renew_content, new Object[]{a}));
        xLAlarmDialog.setRightBtnListener(new f(cVar));
        xLAlarmDialog.setLeftBtnListener(new g(cVar));
        xLAlarmDialog.show();
    }
}
