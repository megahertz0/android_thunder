package com.xunlei.downloadprovider.c.a;

import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.o;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// compiled from: CookieJsonRequest.java
public class h extends o {
    private static final String a;

    static {
        a = h.class.getSimpleName();
    }

    public h(int i, String str, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, str, jSONObject, bVar, aVar);
    }

    public Map<String, String> getHeaders() throws com.android.volley.a {
        Map hashMap = new HashMap();
        hashMap.put("Cookie", g.a());
        return hashMap;
    }
}
