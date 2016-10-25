package com.xunlei.downloadprovider.pushmessage.d;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: PushNetworkHelper.java
final class c implements b<JSONObject> {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        new StringBuilder("onResponse jsonObject").append((JSONObject) obj);
    }
}
