package com.xunlei.downloadprovider.model.protocol.d;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: SearchBarHintManager.java
public final class d implements b<JSONObject> {
    final /* synthetic */ a a;

    public d(a aVar) {
        this.a = aVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        a.a(this.a, jSONObject);
        a.a(jSONObject.toString());
        a.e(this.a).obtainMessage().sendToTarget();
    }
}
