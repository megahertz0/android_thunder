package com.xunlei.downloadprovider.b.c;

import org.json.JSONException;
import org.json.JSONObject;

// compiled from: BpJSONParser.java
public abstract class f extends i {
    private static String a;

    public abstract Object parseJson(JSONObject jSONObject) throws JSONException;

    static {
        a = "BpJSONParser";
    }

    public Object parse(byte[] bArr) {
        try {
            return parseJson(new JSONObject(new String(bArr)));
        } catch (JSONException e) {
            new StringBuilder("failed to create json:").append(e.getMessage());
            this.mErrCode = 1000;
            return null;
        }
    }
}
