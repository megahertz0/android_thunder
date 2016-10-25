package com.xunlei.downloadprovider.frame.user;

import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.toolbox.t;
import com.xunlei.downloadprovider.member.payment.a.e;

// compiled from: GetUserCountsHelper.java
public final class a extends e {
    private static a d;
    private final String a;
    private final String b;
    private final String c;

    private a() {
        this.a = getClass().getSimpleName();
        this.b = "http://jifenshangcheng.m.xunlei.com/cgi-bin/integra_busi_contin?";
        this.c = "http://jifenshangcheng.m.xunlei.com/cgi-bin/integra_busi?";
    }

    public static a a() {
        if (d == null) {
            d = new a();
        }
        return d;
    }

    public final void a(String str) {
        new StringBuilder("postUserOperation userId=").append(str).append(",optionType=0");
        Request tVar = new t(new StringBuilder("http://jifenshangcheng.m.xunlei.com/cgi-bin/integra_busi?").append(new StringBuilder("userId=").append(str).toString()).append("&type=0").toString(), new d(this), new e(this));
        tVar.setShouldCache(false);
        tVar.setRetryPolicy(new f(10000, 1, 1.0f));
        a(tVar);
    }
}
