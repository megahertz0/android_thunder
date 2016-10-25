package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.android.volley.r.b;
import com.umeng.common.inter.ITagManager;
import org.json.JSONObject;

// compiled from: FeedDataManager.java
public final class u implements b<JSONObject> {
    final /* synthetic */ ae a;
    final /* synthetic */ o b;

    public u(o oVar, ae aeVar) {
        this.b = oVar;
        this.a = aeVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        af a = af.a((JSONObject) obj);
        if (a == null || !ITagManager.SUCCESS.equals(a.h)) {
            this.a.a(null);
            return;
        }
        this.b.m = false;
        this.a.a(a);
    }
}
