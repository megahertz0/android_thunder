package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.l;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import java.io.UnsupportedEncodingException;

// compiled from: StringRequest.java
public class t extends Request<String> {
    private final b<String> a;

    protected /* synthetic */ void deliverResponse(Object obj) {
        this.a.onResponse((String) obj);
    }

    public t(int i, String str, b<String> bVar, a aVar) {
        super(i, str, aVar);
        this.a = bVar;
    }

    public t(String str, b<String> bVar, a aVar) {
        this(0, str, bVar, aVar);
    }

    public r<String> parseNetworkResponse(l lVar) {
        Object str;
        try {
            str = new String(lVar.b, f.a(lVar.c));
        } catch (UnsupportedEncodingException e) {
            str = new String(lVar.b);
        }
        return r.a(str, f.a(lVar));
    }
}
