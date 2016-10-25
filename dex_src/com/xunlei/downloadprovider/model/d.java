package com.xunlei.downloadprovider.model;

import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.o;
import com.sina.weibo.sdk.component.GameManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// compiled from: CookieVolleyRequest.java
public final class d extends o {
    private Map<String, String> a;

    public d(String str, b<JSONObject> bVar, a aVar) {
        super(0, str, null, bVar, aVar);
        this.a = new HashMap(1);
        this.a.put("Charset", GameManager.DEFAULT_CHARSET);
    }

    public final Map<String, String> getHeaders() throws com.android.volley.a {
        return this.a;
    }
}
