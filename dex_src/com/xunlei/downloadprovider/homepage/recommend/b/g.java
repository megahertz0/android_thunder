package com.xunlei.downloadprovider.homepage.recommend.b;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: CounterNetWorkHelper.java
final class g implements b<JSONObject> {
    final /* synthetic */ b a;

    g(b bVar) {
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        new StringBuilder("sendPlay onResponse jsonObject=").append((JSONObject) obj);
    }
}
