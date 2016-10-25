package com.xunlei.downloadprovider.model.protocol.networkcheck;

import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.b.c.f;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: NetworkSpeedCheckParser.java
public final class i extends f {
    public final Object parseJson(JSONObject jSONObject) throws JSONException {
        try {
            return jSONObject.getInt(Impl.COLUMN_STATUS) == 200 ? jSONObject.getString("test_url") : null;
        } catch (JSONException e) {
            this.mErrCode = 1000;
            return null;
        }
    }
}
