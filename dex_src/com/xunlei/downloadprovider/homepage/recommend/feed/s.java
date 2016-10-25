package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.android.volley.r.b;
import com.umeng.common.inter.ITagManager;
import org.json.JSONObject;

// compiled from: FeedDataManager.java
public final class s implements b<JSONObject> {
    final /* synthetic */ ah a;
    final /* synthetic */ o b;

    public s(o oVar, ah ahVar) {
        this.b = oVar;
        this.a = ahVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        ai a = ai.a((JSONObject) obj);
        if (a == null || !ITagManager.SUCCESS.equals(a.d)) {
            this.a.a(null);
            return;
        }
        this.b.m = false;
        this.a.a(a);
    }
}
