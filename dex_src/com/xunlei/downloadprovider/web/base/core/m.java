package com.xunlei.downloadprovider.web.base.core;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: DefaultJsInterface.java
final class m implements a {
    final /* synthetic */ String a;
    final /* synthetic */ DefaultJsInterface b;

    m(DefaultJsInterface defaultJsInterface, String str) {
        this.b = defaultJsInterface;
        this.a = str;
    }

    public final void onErrorResponse(w wVar) {
        int i = -1;
        if (!(wVar == null || wVar.a == null)) {
            i = wVar.a.a;
        }
        this.b.httpRequestCallback(this.a, false, i, BuildConfig.VERSION_NAME);
    }
}
