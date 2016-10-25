package com.xunlei.downloadprovider.member.a.a;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: HighSpeedDatapool.java
public final class c {
    static Map<String, String> a;
    static Map<String, String> b;

    static {
        a = new HashMap();
        Map hashMap = new HashMap();
        b = hashMap;
        hashMap.put("before_trial-button", "\u52a0\u901f\u8bd5\u7528");
        b.put("before_trial-text", "\u514d\u8d39\u8bd5\u7528\u4f1a\u5458\u52a0\u901f\u7279\u6743,\u66f4\u5feb\u5b8c\u6210\u4e0b\u8f7d");
        b.put("on_trial-button", "\u5f00\u901a\u4f1a\u5458");
        b.put("on_trial-text", "\u4f1a\u5458\u52a0\u901f:\u8fd8\u5269%s,\u4f1a\u5458\u4e0d\u9650\u91cf");
        b.put("after_trial-button", "\u5f00\u901a\u4f1a\u5458");
        b.put("after_trial-text", "\u8bd5\u7528\u7ed3\u675f,\u4f1a\u5458%s\u5185\u4e0b\u5b8c");
        b.put("netspeed", "400");
        b.put("ending_trial-button", "\u5f00\u901a\u4f1a\u5458");
        b.put("ending_trial-text", "\u8bd5\u7528\u5c06\u7ed3\u675f,\u4f1a\u5458%s\u5185\u4e0b\u5b8c");
    }

    protected static void a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject(str2);
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str3 = (String) keys.next();
            String toString = jSONObject.get(str3).toString();
            new StringBuilder("key:").append(str3).append("--").append(toString);
            try {
                JSONObject jSONObject2 = new JSONObject(toString);
                if (TextUtils.isEmpty(str)) {
                    a(str3, toString);
                } else {
                    a(str + com.xunlei.download.proguard.c.q + str3, toString);
                }
            } catch (Exception e) {
                new StringBuilder("really value,not a json string,key=").append(str).append(com.xunlei.download.proguard.c.q).append(str3);
                if (TextUtils.isEmpty(str)) {
                    a.put(str3, toString);
                } else {
                    a.put(str + com.xunlei.download.proguard.c.q + str3, toString);
                }
            }
        }
    }

    protected static void a() {
        for (String str : a.keySet()) {
            new StringBuilder().append(str).append("==").append((String) a.get(str));
        }
    }

    protected static boolean b() {
        return a.isEmpty();
    }

    protected static String a(String str) {
        String str2 = BuildConfig.VERSION_NAME;
        if (a.containsKey(str)) {
            return (String) a.get(str);
        }
        return b.containsKey(str) ? (String) b.get(str) : str2;
    }
}
