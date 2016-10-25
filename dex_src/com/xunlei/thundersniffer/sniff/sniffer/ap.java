package com.xunlei.thundersniffer.sniff.sniffer;

import com.android.volley.r.b;
import org.json.JSONException;
import org.json.JSONObject;

final class ap implements b<JSONObject> {
    final /* synthetic */ ao a;

    ap(ao aoVar) {
        this.a = aoVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        jSONObject.toString();
        try {
            jSONObject.getInt("ret");
        } catch (JSONException e) {
        }
    }
}
