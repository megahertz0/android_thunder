package com.xunlei.downloadprovider.model.protocol.networkcheck;

import android.os.Message;
import com.android.volley.r.b;
import com.xunlei.downloadprovider.web.core.JsInterface;
import org.json.JSONObject;

// compiled from: IPAddressErrorActivity.java
final class g implements b<JSONObject> {
    g() {
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        IPAddressErrorActivity.a;
        IPAddressErrorActivity.a;
        new StringBuilder("onSuccess() result=").append(jSONObject);
        Message.obtain(IPAddressErrorActivity.g, JsInterface.MSG_JS_OPENWINDOW_WITH_DOWNLOADLISTEX, jSONObject.optString("app_available")).sendToTarget();
    }
}
