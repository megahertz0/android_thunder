package com.xunlei.downloadprovider.pushmessage.d;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: PushNetworkHelper.java
public final class e implements b<JSONObject> {
    final /* synthetic */ b a;

    public e(b bVar) {
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        try {
            new StringBuilder("onResponse jsonObject").append(jSONObject);
            b.a(jSONObject);
        } catch (Exception e) {
        }
    }
}
