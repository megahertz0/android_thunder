package com.xunlei.downloadprovidercommon.b.a;

import com.android.volley.l;
import com.android.volley.n;
import com.android.volley.r;
import com.android.volley.r.b;
import com.android.volley.toolbox.f;
import org.json.JSONObject;

// compiled from: SigJsonObjectRequest.java
public final class a extends b<JSONObject> {
    public a(String str, JSONObject jSONObject, b<JSONObject> bVar, com.android.volley.r.a aVar) {
        super(1, str, jSONObject == null ? null : jSONObject.toString(), bVar, aVar);
    }

    public a(String str, b<JSONObject> bVar, com.android.volley.r.a aVar) {
        super(0, str, null, bVar, aVar);
    }

    public a(String str, b<JSONObject> bVar, com.android.volley.r.a aVar, byte b) {
        this(str, bVar, aVar);
    }

    protected final r<JSONObject> parseNetworkResponse(l lVar) {
        try {
            return r.a(new JSONObject(new String(lVar.b, f.a(lVar.c, "utf-8"))), f.a(lVar));
        } catch (Throwable e) {
            return r.a(new n(e));
        } catch (Throwable e2) {
            return r.a(new n(e2));
        }
    }
}
