package com.xunlei.downloadprovider.web.core.a;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: AdBlockHelper.java
final class c implements b<JSONObject> {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        new StringBuilder("config downloaded:http://m.sjzhushou.com/xlconfig/adblock.txt\n").append(jSONObject);
        a a = b.a(jSONObject);
        if (a != null) {
            this.a.a = a;
        }
    }
}
