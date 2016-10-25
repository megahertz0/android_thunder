package com.xunlei.downloadprovider.model.protocol.b;

import com.xunlei.downloadprovider.b.c.f;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: FunResourceParser.java
public class e extends f {
    private static final String a;

    static {
        a = e.class.getSimpleName();
    }

    public Object parseJson(JSONObject jSONObject) throws JSONException {
        try {
            new StringBuilder("finish download JSONData --> ").append(System.currentTimeMillis() - a.a);
            return f.a(jSONObject);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
