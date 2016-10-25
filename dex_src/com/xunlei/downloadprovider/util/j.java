package com.xunlei.downloadprovider.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

// compiled from: JsSharedPreference.java
public final class j {
    public static Map<String, String> a(String str) {
        Map<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                hashMap.put(str2, String.valueOf(jSONObject.get(str2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}
