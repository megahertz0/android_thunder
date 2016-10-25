package com.xunlei.downloadprovider.homepage.interest.a;

import com.android.volley.r.b;
import com.umeng.common.inter.ITagManager;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: InterestNetworkHelper.java
final class d implements b<JSONObject> {
    final /* synthetic */ com.xunlei.downloadprovider.search.b.b a;
    final /* synthetic */ a b;

    d(a aVar, com.xunlei.downloadprovider.search.b.b bVar) {
        this.b = aVar;
        this.a = bVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        try {
            this.a.a(Boolean.valueOf(ITagManager.SUCCESS.equalsIgnoreCase(((JSONObject) obj).getString("result"))));
        } catch (JSONException e) {
            this.a.a(Boolean.valueOf(false));
        }
    }
}
