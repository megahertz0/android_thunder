package com.xunlei.downloadprovider.web.base.core;

import android.text.TextUtils;
import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.t;
import java.io.UnsupportedEncodingException;

// compiled from: DefaultJsInterface.java
final class n extends t {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ DefaultJsInterface c;

    n(DefaultJsInterface defaultJsInterface, int i, String str, b bVar, a aVar, String str2, String str3) {
        this.c = defaultJsInterface;
        this.a = str2;
        this.b = str3;
        super(i, str, bVar, aVar);
    }

    public final byte[] getBody() throws com.android.volley.a {
        if (!(TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.b))) {
            try {
                return this.a.getBytes(this.b);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return super.getBody();
    }
}
