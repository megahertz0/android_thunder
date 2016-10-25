package com.xunlei.downloadprovider.web.base.model;

import com.android.volley.r.b;
import com.umeng.common.inter.ITagManager;
import org.json.JSONObject;

// compiled from: ShortMovieDetailDataLoader.java
final class k implements b<JSONObject> {
    final /* synthetic */ d a;

    k(d dVar) {
        this.a = dVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String optString;
        u uVar = null;
        JSONObject jSONObject = (JSONObject) obj;
        new StringBuilder("response=>").append(jSONObject.toString());
        if (jSONObject != null) {
            optString = jSONObject.optString("result");
            if (ITagManager.SUCCESS.contentEquals(optString)) {
                uVar = u.a(jSONObject.optJSONObject("video_info"));
            }
        } else {
            optString = null;
        }
        this.a.e.a(optString, uVar);
    }
}
