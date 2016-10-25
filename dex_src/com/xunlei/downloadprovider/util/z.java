package com.xunlei.downloadprovider.util;

import android.text.TextUtils;
import java.util.HashMap;
import org.json.JSONObject;

// compiled from: UrlConfig.java
public final class z {
    private static HashMap<String, String> a;

    static {
        a = new HashMap();
    }

    public static String a() {
        String str = (String) a.get("search_associate_url");
        return (TextUtils.isEmpty(str) || !str.equals("http://m.sjzhushou.com/cgi-bin/finder?")) ? "http://m.sjzhushou.com/cgi-bin/finder?" : str;
    }

    static void a(JSONObject jSONObject) {
        CharSequence optString = jSONObject.optString("search_associate_url");
        if (!TextUtils.isEmpty(optString)) {
            a.put("search_associate_url", optString);
        }
    }
}
