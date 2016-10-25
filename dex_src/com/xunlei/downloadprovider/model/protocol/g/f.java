package com.xunlei.downloadprovider.model.protocol.g;

import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XunleiScanCodeBox.java
public final class f extends com.xunlei.downloadprovider.b.c.f {
    final /* synthetic */ e a;

    public f(e eVar) {
        this.a = eVar;
    }

    public final Object parseJson(JSONObject jSONObject) throws JSONException {
        new StringBuilder("JSON:").append(jSONObject.toString());
        this.mErrCode = jSONObject.getInt("status");
        if (this.mErrCode != 0) {
            return null;
        }
        Object a = k.a(jSONObject);
        if (a != null) {
            return a;
        }
        this.mErrCode = -1;
        return a;
    }
}
