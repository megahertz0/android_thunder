package com.xunlei.downloadprovider.web.base.core;

import com.android.volley.r.b;
import com.xunlei.common.register.XLRegErrorCode;

// compiled from: DefaultJsInterface.java
final class l implements b<String> {
    final /* synthetic */ String a;
    final /* synthetic */ DefaultJsInterface b;

    l(DefaultJsInterface defaultJsInterface, String str) {
        this.b = defaultJsInterface;
        this.a = str;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        this.b.httpRequestCallback(this.a, true, XLRegErrorCode.REG_SUCCEED, (String) obj);
    }
}
