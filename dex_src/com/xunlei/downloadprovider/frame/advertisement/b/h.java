package com.xunlei.downloadprovider.frame.advertisement.b;

import com.android.volley.l;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.f;
import com.android.volley.toolbox.t;
import java.io.UnsupportedEncodingException;

// compiled from: UTF8StringRequest.java
final class h extends t {
    public h(String str, b<String> bVar, a aVar) {
        super(str, bVar, aVar);
    }

    protected final r<String> parseNetworkResponse(l lVar) {
        try {
            return r.a(new String(lVar.b, "utf-8"), f.a(lVar));
        } catch (UnsupportedEncodingException e) {
            try {
                e.printStackTrace();
                return r.a(null, f.a(lVar));
            } catch (Throwable th) {
                return r.a(null, f.a(lVar));
            }
        }
    }
}
