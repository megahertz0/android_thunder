package com.xunlei.downloadprovider.homepage.recommend.b;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: CounterNetWorkHelper.java
final class e implements b<JSONObject> {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        new StringBuilder("sendShare onResponse jsonObject=").append((JSONObject) obj);
    }
}
