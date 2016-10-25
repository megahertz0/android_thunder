package com.xunlei.downloadprovider.member.payment.ui;

import com.android.volley.r.b;
import com.xunlei.common.base.XLLog;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: PaymentH5Activity.java
final class ao implements b<String> {
    final /* synthetic */ String a;
    final /* synthetic */ PaymentH5Activity b;

    ao(PaymentH5Activity paymentH5Activity, String str) {
        this.b = paymentH5Activity;
        this.a = str;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        XLLog.d("liu.js", new StringBuilder("getPriceConfig--response=").append(str).toString());
        this.b.b((int) XZBDevice.DOWNLOAD_LIST_ALL);
        PaymentH5Activity.a(this.b, this.a, str);
    }
}
