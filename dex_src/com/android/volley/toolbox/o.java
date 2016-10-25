package com.android.volley.toolbox;

import com.android.volley.l;
import com.android.volley.n;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: JsonObjectRequest.java
public class o extends p<JSONObject> {
    public o(int i, String str, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, str, jSONObject == null ? null : jSONObject.toString(), bVar, aVar);
    }

    public o(String str, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        this(jSONObject == null ? 0 : 1, str, jSONObject, bVar, aVar);
    }

    public r<JSONObject> parseNetworkResponse(l lVar) {
        try {
            return r.a(new JSONObject(new String(lVar.b, f.a(lVar.c, "utf-8"))), f.a(lVar));
        } catch (Throwable e) {
            return r.a(new n(e));
        } catch (Throwable e2) {
            return r.a(new n(e2));
        }
    }
}
