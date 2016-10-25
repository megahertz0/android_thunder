package com.xunlei.downloadprovider.model.protocol.c.c;

import com.xunlei.downloadprovider.b.c.f;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: QueryGroupResSupportParser.java
public final class d extends f {
    public final Object parseJson(JSONObject jSONObject) throws JSONException {
        return Integer.valueOf(jSONObject.getInt("rtn"));
    }
}
