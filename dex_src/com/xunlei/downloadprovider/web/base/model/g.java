package com.xunlei.downloadprovider.web.base.model;

import com.android.volley.r.b;
import com.umeng.common.inter.ITagManager;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONObject;

// compiled from: ShortMovieDetailDataLoader.java
public final class g implements b<JSONObject> {
    final /* synthetic */ d a;

    public g(d dVar) {
        this.a = dVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        new StringBuilder("response=>").append(jSONObject.toString());
        if (jSONObject != null) {
            Object optString = jSONObject.optString("result");
            if (ITagManager.SUCCESS.contentEquals(optString)) {
                this.a.e.c();
            } else {
                this.a.e.a(SimpleLog.LOG_LEVEL_ERROR, optString);
            }
        }
    }
}
