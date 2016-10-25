package com.xunlei.downloadprovider.homepage.recommend.feed;

import com.android.volley.r.b;
import com.umeng.common.inter.ITagManager;
import org.json.JSONObject;

// compiled from: FeedDataManager.java
final class w implements b<JSONObject> {
    final /* synthetic */ String a;
    final /* synthetic */ o b;

    w(o oVar, String str) {
        this.b = oVar;
        this.a = str;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject != null && this.b.c != null) {
            if (ITagManager.SUCCESS.contentEquals(jSONObject.optString("result"))) {
                this.b.c.a(true, this.a);
            } else {
                this.b.c.a(false, this.a);
            }
        }
    }
}
