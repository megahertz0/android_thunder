package com.xunlei.downloadprovider.model.protocol.networkcheck;

import android.os.Message;
import com.android.volley.r.b;
import com.xunlei.tdlive.sdk.IHost;
import org.json.JSONObject;

// compiled from: IPAddressErrorActivity.java
final class e implements b<JSONObject> {
    e() {
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        IPAddressErrorActivity.a;
        IPAddressErrorActivity.a;
        new StringBuilder("onSuccess() result=").append(jSONObject);
        Message.obtain(IPAddressErrorActivity.g, IHost.HOST_NOFITY_PAGE_SELECTED, jSONObject.optString("app_available")).sendToTarget();
    }
}
