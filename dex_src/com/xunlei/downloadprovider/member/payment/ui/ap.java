package com.xunlei.downloadprovider.member.payment.ui;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.common.base.XLLog;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: PaymentH5Activity.java
final class ap implements a {
    final /* synthetic */ String a;
    final /* synthetic */ PaymentH5Activity b;

    ap(PaymentH5Activity paymentH5Activity, String str) {
        this.b = paymentH5Activity;
        this.a = str;
    }

    public final void onErrorResponse(w wVar) {
        XLLog.d("liu.js", new StringBuilder("getPriceConfig--error=").append(wVar).toString());
        this.b.b((int) XZBDevice.DOWNLOAD_LIST_ALL);
        PaymentH5Activity.a(this.b, this.a, com.umeng.a.d);
    }
}
