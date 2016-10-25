package com.xunlei.downloadprovidershare.b;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: WeChatReportHelper.java
final class f implements b<JSONObject> {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        jSONObject.optString("result");
        String optString = jSONObject.optString("storid");
        if (d.a(this.a) != null) {
            d.a(this.a).a(optString);
            d.b(this.a);
        }
    }
}
