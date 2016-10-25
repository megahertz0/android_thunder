package com.xunlei.downloadprovider.member.payment.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;

// compiled from: BasePayActivity.java
final class k implements OnClickListener {
    final /* synthetic */ XLPayParam a;
    final /* synthetic */ d b;
    final /* synthetic */ BasePayActivity c;

    k(BasePayActivity basePayActivity, XLPayParam xLPayParam, d dVar) {
        this.c = basePayActivity;
        this.a = xLPayParam;
        this.b = dVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        XLAlipayParam a = PayUtil.a(this.a);
        a.mActivity = this.c;
        this.c.a(a);
        this.b.cancel();
    }
}
