package com.xunlei.thundersniffer.sniff.sniffer;

import com.android.volley.r.b;
import org.json.JSONObject;

final class j implements b<JSONObject> {
    final /* synthetic */ SnifferSvrGetOperation a;

    j(SnifferSvrGetOperation snifferSvrGetOperation) {
        this.a = snifferSvrGetOperation;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        new StringBuilder().append(this.a.d.a).append(" sniffer.get :").append(jSONObject.toString());
        SnifferSvrGetOperation.a(this.a).b("Sniffer.SnifferSvrGetOperation", "SvrGetRequest End");
        this.a.a(jSONObject);
    }
}
