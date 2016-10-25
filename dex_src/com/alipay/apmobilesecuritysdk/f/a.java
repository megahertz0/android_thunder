package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import org.json.JSONObject;

public final class a {
    private static b a(String str) {
        try {
            if (!com.alipay.b.a.a.a.a.a(str)) {
                JSONObject jSONObject = new JSONObject(str);
                return new b(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"));
            }
        } catch (Throwable e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
        }
        return null;
    }

    public static synchronized void a() {
        synchronized (a.class) {
        }
    }

    public static synchronized void a(Context context) {
        synchronized (a.class) {
            com.alipay.apmobilesecuritysdk.g.a.a(context, "vkeyid_profiles_v3", "deviceid", com.umeng.a.d);
            com.alipay.apmobilesecuritysdk.g.a.a("wxcasxx_v3", "wxcasxx", com.umeng.a.d);
        }
    }

    public static synchronized void a(Context context, b bVar) {
        synchronized (a.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", bVar.a());
                jSONObject.put("deviceInfoHash", bVar.b());
                jSONObject.put("timestamp", bVar.c());
                String toString = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.g.a.a(context, "vkeyid_profiles_v3", "deviceid", toString);
                com.alipay.apmobilesecuritysdk.g.a.a("wxcasxx_v3", "wxcasxx", toString);
            } catch (Throwable e) {
                com.alipay.apmobilesecuritysdk.c.a.a(e);
            }
        }
    }

    public static synchronized b b() {
        b a;
        synchronized (a.class) {
            String a2 = com.alipay.apmobilesecuritysdk.g.a.a("wxcasxx_v3", "wxcasxx");
            a = com.alipay.b.a.a.a.a.a(a2) ? null : a(a2);
        }
        return a;
    }

    public static synchronized b b(Context context) {
        b a;
        synchronized (a.class) {
            String a2 = com.alipay.apmobilesecuritysdk.g.a.a(context, "vkeyid_profiles_v3", "deviceid");
            if (com.alipay.b.a.a.a.a.a(a2)) {
                a2 = com.alipay.apmobilesecuritysdk.g.a.a("wxcasxx_v3", "wxcasxx");
            }
            a = a(a2);
        }
        return a;
    }

    public static synchronized b c(Context context) {
        b a;
        synchronized (a.class) {
            String a2 = com.alipay.apmobilesecuritysdk.g.a.a(context, "vkeyid_profiles_v3", "deviceid");
            a = com.alipay.b.a.a.a.a.a(a2) ? null : a(a2);
        }
        return a;
    }
}
