package com.xunlei.downloadprovider.homepage.recommend.b;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: CounterNetWorkHelper.java
public final class i implements b<JSONObject> {
    final /* synthetic */ b a;
    final /* synthetic */ b b;

    public i(b bVar, b bVar2) {
        this.b = bVar;
        this.a = bVar2;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        new StringBuilder("sendReport onResponse jsonObject=").append(jSONObject);
        if (this.a != null) {
            this.a.onResponse(jSONObject);
        }
    }
}
