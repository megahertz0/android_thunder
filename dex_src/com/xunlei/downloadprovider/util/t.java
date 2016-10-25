package com.xunlei.downloadprovider.util;

import com.android.volley.r.b;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONObject;

// compiled from: OnlineConfigure.java
final class t implements b<JSONObject> {
    final /* synthetic */ r a;

    t(r rVar) {
        this.a = rVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        String str = r.a;
        new StringBuilder("downloadOnlineConfigure - ").append(jSONObject.toString());
        try {
            this.a.a(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.a.a(jSONObject, SimpleLog.LOG_LEVEL_DEBUG);
    }
}
