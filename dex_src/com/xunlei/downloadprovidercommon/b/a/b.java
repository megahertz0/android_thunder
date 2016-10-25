package com.xunlei.downloadprovidercommon.b.a;

import com.android.volley.l;
import com.android.volley.r;
import com.android.volley.r.a;
import java.io.UnsupportedEncodingException;

// compiled from: SigJsonRequest.java
public abstract class b<T> extends c<T> {
    protected abstract r<T> parseNetworkResponse(l lVar);

    public b(int i, String str, String str2, com.android.volley.r.b<T> bVar, a aVar) {
        super(i, str, str2, bVar, aVar);
    }

    public String getBodyContentType() {
        return b;
    }

    public byte[] getBody() throws com.android.volley.a {
        if (!(this.c == null || getMethod() == 0)) {
            try {
                return this.c.c().getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
