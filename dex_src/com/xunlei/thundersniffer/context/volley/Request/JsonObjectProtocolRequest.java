package com.xunlei.thundersniffer.context.volley.Request;

import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.o;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class JsonObjectProtocolRequest extends o {
    protected HashMap<String, String> mCookie;

    public JsonObjectProtocolRequest(String str, b<JSONObject> bVar, a aVar) {
        super(0, str, null, bVar, aVar);
    }

    public JsonObjectProtocolRequest(int i, String str, b<JSONObject> bVar, a aVar) {
        super(i, str, null, bVar, aVar);
    }

    public JsonObjectProtocolRequest(int i, String str, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, str, jSONObject, bVar, aVar);
    }

    public JsonObjectProtocolRequest(String str, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(str, jSONObject, bVar, aVar);
    }

    public Map<String, String> getHeaders() throws com.android.volley.a {
        Map<String, String> headers = super.getHeaders();
        if (this.mCookie == null || this.mCookie.isEmpty()) {
            return headers;
        }
        HashMap hashMap = new HashMap();
        if (headers != null) {
            hashMap.putAll(headers);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.mCookie.entrySet()) {
            stringBuilder.append(" ").append((String) entry.getKey()).append("=").append((String) entry.getValue()).append(";");
        }
        hashMap.put("Cookie", stringBuilder.toString());
        return hashMap;
    }

    public HashMap<String, String> getCookie() {
        if (this.mCookie == null) {
            this.mCookie = new HashMap();
        }
        return this.mCookie;
    }
}
