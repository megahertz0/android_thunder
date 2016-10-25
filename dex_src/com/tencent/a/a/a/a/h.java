package com.tencent.a.a.a.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Base64;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.wxop.stat.common.f;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import org.json.JSONObject;

public final class h {
    static String a(Context context) {
        try {
            if (a(context, MsgConstant.PERMISSION_READ_PHONE_STATE)) {
                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId != null) {
                    return deviceId;
                }
            }
        } catch (Throwable th) {
        }
        return a.d;
    }

    private static void a(String str, Throwable th) {
    }

    static void a(JSONObject jSONObject, String str, String str2) {
        if (b(str2)) {
            jSONObject.put(str, str2);
        }
    }

    static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            a("checkPermission error", th);
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String b(android.content.Context r3) {
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.a.a.a.a.h.b(android.content.Context):java.lang.String");
        /*
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r0 = a(r3, r0);
        if (r0 == 0) goto L_0x0031;
    L_0x0009:
        r0 = "wifi";
        r0 = r3.getSystemService(r0);	 Catch:{ Exception -> 0x0021 }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Exception -> 0x0021 }
        if (r0 != 0) goto L_0x0018;
    L_0x0014:
        r0 = "";
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r0.getConnectionInfo();	 Catch:{ Exception -> 0x0021 }
        r0 = r0.getMacAddress();	 Catch:{ Exception -> 0x0021 }
        goto L_0x0017;
    L_0x0021:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r2 = "get wifi address error";
        r1.<init>(r2);
        r1.append(r0);
        r0 = "";
        goto L_0x0017;
    L_0x0031:
        r0 = "";
        goto L_0x0017;
        */
    }

    static boolean b(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    public static boolean c(String str) {
        return str != null && str.trim().length() >= 40;
    }

    static String f(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(f.b(Base64.decode(str.getBytes(GameManager.DEFAULT_CHARSET), 0)), GameManager.DEFAULT_CHARSET).trim().replace("\t", a.d).replace("\n", a.d).replace("\r", a.d);
        } catch (Throwable th) {
            a("decode error", th);
            return str;
        }
    }

    static String g(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(Base64.encode(f.a(str.getBytes(GameManager.DEFAULT_CHARSET)), 0), GameManager.DEFAULT_CHARSET).trim().replace("\t", a.d).replace("\n", a.d).replace("\r", a.d);
        } catch (Throwable th) {
            a("decode error", th);
            return str;
        }
    }
}
