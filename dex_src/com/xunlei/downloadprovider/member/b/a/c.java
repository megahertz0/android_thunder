package com.xunlei.downloadprovider.member.b.a;

import android.text.TextUtils;
import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.w;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: VipRenewalConfigClient.java
public final class c implements a, b<String> {
    static boolean a;
    private static String c;
    String b;
    private com.xunlei.downloadprovider.member.b.a.a d;

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        a();
        try {
            if (!TextUtils.isEmpty(str)) {
                String a = a.a(new String(str.getBytes(CharsetConvert.ISO_8859_1), CharsetConvert.UTF_8));
                if (this.d != null && a.d(a)) {
                    this.d.b();
                }
            } else if (this.d == null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        c = "VipRenewalConfigClient";
    }

    public c(com.xunlei.downloadprovider.member.b.a.a aVar) {
        this.b = BuildConfig.VERSION_NAME;
        this.d = aVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("getVipRenewalConfig--error=").append(wVar);
        a.c(this.b);
        a();
    }

    final void a() {
        a = false;
        this.b = BuildConfig.VERSION_NAME;
    }
}
