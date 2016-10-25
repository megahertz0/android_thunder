package com.android.volley.toolbox;

import com.android.volley.l;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import org.json.JSONArray;

// compiled from: JsonArrayRequest.java
public final class n extends p<JSONArray> {
    public n(String str, b<JSONArray> bVar, a aVar) {
        super(0, str, null, bVar, aVar);
    }

    protected final r<JSONArray> parseNetworkResponse(l lVar) {
        try {
            return r.a(new JSONArray(new String(lVar.b, f.a(lVar.c, "utf-8"))), f.a(lVar));
        } catch (Throwable e) {
            return r.a(new com.android.volley.n(e));
        } catch (Throwable e2) {
            return r.a(new com.android.volley.n(e2));
        }
    }
}
