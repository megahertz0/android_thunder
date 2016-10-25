package com.xunlei.downloadprovider.member.payment.a;

import android.content.Context;
import android.os.Handler;
import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.toolbox.t;

// compiled from: ActivationBox.java
public final class a extends e {
    String a;
    public boolean b;
    private final String c;
    private boolean d;

    public a() {
        this.c = "http://dypay.vip.xunlei.com/";
        this.d = false;
        this.a = null;
        this.b = false;
    }

    public a(Context context, String str, String str2, String str3, String str4, String str5, Handler handler) {
        this.c = "http://dypay.vip.xunlei.com/";
        this.d = false;
        this.a = null;
        this.b = false;
        Request tVar = new t(new StringBuilder("http://dypay.vip.xunlei.com/gdscore/charge?uid=").append(str).append("&ticket=").append(str2).append("&verifycode=").append(str3).append("&verifykey=").append(str4).append("&act=").append(str5).append("&client=all&callback=callback&resptype=json").toString(), new b(this, handler), new c(this, context, handler));
        tVar.setShouldCache(false);
        tVar.setRetryPolicy(new f(12000, 1, 1.0f));
        a(tVar);
    }
}
