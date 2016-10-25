package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import com.alipay.b.a.a.a.a;
import com.alipay.sdk.cons.b;
import com.umeng.message.MsgConstant;
import org.json.JSONObject;

public final class d {
    private static c a(String str) {
        try {
            if (!a.a(str)) {
                JSONObject jSONObject = new JSONObject(str);
                return new c(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"), jSONObject.optString(b.c), jSONObject.optString(MsgConstant.KEY_UTDID));
            }
        } catch (Throwable e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
        }
        return null;
    }

    public static synchronized void a() {
        synchronized (d.class) {
        }
    }

    public static synchronized void a(Context context) {
        synchronized (d.class) {
            com.alipay.apmobilesecuritysdk.g.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4", com.umeng.a.d);
            com.alipay.apmobilesecuritysdk.g.a.a("wxcasxx_v4", "key_wxcasxx_v4", com.umeng.a.d);
        }
    }

    public static synchronized void a(Context context, c cVar) {
        synchronized (d.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", cVar.a());
                jSONObject.put("deviceInfoHash", cVar.b());
                jSONObject.put("timestamp", cVar.c());
                jSONObject.put(b.c, cVar.d());
                jSONObject.put(MsgConstant.KEY_UTDID, cVar.e());
                String toString = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.g.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4", toString);
                com.alipay.apmobilesecuritysdk.g.a.a("wxcasxx_v4", "key_wxcasxx_v4", toString);
            } catch (Throwable e) {
                com.alipay.apmobilesecuritysdk.c.a.a(e);
            }
        }
    }

    public static synchronized c b() {
        c a;
        synchronized (d.class) {
            String a2 = com.alipay.apmobilesecuritysdk.g.a.a("wxcasxx_v4", "key_wxcasxx_v4");
            a = a.a(a2) ? null : a(a2);
        }
        return a;
    }

    public static synchronized c b(Context context) {
        c a;
        synchronized (d.class) {
            String a2 = com.alipay.apmobilesecuritysdk.g.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (a.a(a2)) {
                a2 = com.alipay.apmobilesecuritysdk.g.a.a("wxcasxx_v4", "key_wxcasxx_v4");
            }
            a = a(a2);
        }
        return a;
    }

    public static synchronized c c(Context context) {
        c a;
        synchronized (d.class) {
            String a2 = com.alipay.apmobilesecuritysdk.g.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            a = a.a(a2) ? null : a(a2);
        }
        return a;
    }
}
