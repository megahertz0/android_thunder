package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import com.umeng.a;

public class StatMid {
    private static StatLogger a;
    private static DeviceInfo b;

    static {
        a = k.b();
        b = null;
    }

    static synchronized DeviceInfo a(Context context) {
        DeviceInfo a;
        synchronized (StatMid.class) {
            try {
                a a2 = a.a(context);
                DeviceInfo a3 = a(a2.d(DeviceInfo.TAG_FLAG, null));
                a.d(new StringBuilder("get device info from internal storage:").append(a3).toString());
                DeviceInfo a4 = a(a2.f(DeviceInfo.TAG_FLAG, null));
                a.d(new StringBuilder("get device info from setting.system:").append(a4).toString());
                a = a(a2.b(DeviceInfo.TAG_FLAG, null));
                a.d(new StringBuilder("get device info from SharedPreference:").append(a).toString());
                a = a(a, a4, a3);
                b = a;
                if (a == null) {
                    b = new DeviceInfo();
                }
                a = n.a(context).b(context);
                if (a != null) {
                    b.d(a.getImei());
                    b.e(a.getMac());
                    b.b(a.getUserType());
                }
            } catch (Object th) {
                a.e(th);
            }
            a = b;
        }
        return a;
    }

    static DeviceInfo a(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
        return (deviceInfo == null || deviceInfo2 == null) ? deviceInfo == null ? deviceInfo2 != null ? deviceInfo2 : null : deviceInfo : deviceInfo.a(deviceInfo2) >= 0 ? deviceInfo : deviceInfo2;
    }

    static DeviceInfo a(DeviceInfo deviceInfo, DeviceInfo deviceInfo2, DeviceInfo deviceInfo3) {
        return a(a(deviceInfo, deviceInfo2), a(deviceInfo2, deviceInfo3));
    }

    private static DeviceInfo a(String str) {
        return str != null ? DeviceInfo.a(k.d(str)) : null;
    }

    public static DeviceInfo getDeviceInfo(Context context) {
        if (context == null) {
            a.error((Object) "Context for StatConfig.getDeviceInfo is null.");
            return null;
        }
        if (b == null) {
            a(context);
        }
        return b;
    }

    public static String getMid(Context context) {
        if (b == null) {
            getDeviceInfo(context);
        }
        return b.getMid();
    }

    public static void updateDeviceInfo(Context context, String str) {
        try {
            getDeviceInfo(context);
            b.c(str);
            b.a(b.a() + 1);
            b.a(System.currentTimeMillis());
            String toString = b.c().toString();
            a.d(new StringBuilder("save DeviceInfo:").append(toString).toString());
            toString = k.c(toString).replace("\n", a.d);
            a a = a.a(context);
            a.c(DeviceInfo.TAG_FLAG, toString);
            a.e(DeviceInfo.TAG_FLAG, toString);
            a.a(DeviceInfo.TAG_FLAG, toString);
        } catch (Object th) {
            a.e(th);
        }
    }
}
