package com.xunlei.downloadprovider.member.payment.ui;

import android.webkit.WebView;
import com.umeng.a;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.core.p;

// compiled from: PaymentSuccessActivity.java
final class ar extends p {
    final /* synthetic */ PaymentSuccessActivity a;

    ar(PaymentSuccessActivity paymentSuccessActivity) {
        this.a = paymentSuccessActivity;
    }

    public final boolean a(WebView webView, String str) {
        if (this.a.i.a.getOriginalUrl().equals(str)) {
            return super.a(webView, str);
        }
        BrowserUtil.a();
        BrowserUtil.a(this.a, str, a.d);
        return true;
    }
}
