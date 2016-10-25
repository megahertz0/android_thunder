package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.reflect.a;
import com.xunlei.mediaserver.Utility;

public class d {
    private static String a;
    private static String b;
    private static String c;

    static {
        a = null;
        b = null;
        c = null;
    }

    public static String a() {
        return VERSION.SDK_INT > 8 ? Build.SERIAL : null;
    }

    public static String a(Context context) {
        if (b == null) {
            String c = c(context);
            String b = b(context);
            b = new StringBuilder("a-").append(com.xiaomi.channel.commonutils.string.d.b(c + b + a())).toString();
        }
        return b;
    }

    @TargetApi(17)
    public static int b() {
        if (VERSION.SDK_INT < 17) {
            return -1;
        }
        Object a = a.a("android.os.UserHandle", "myUserId", new Object[0]);
        return a != null ? ((Integer) Integer.class.cast(a)).intValue() : -1;
    }

    public static String b(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    public static String c(Context context) {
        if (a != null) {
            return a;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = telephonyManager.getDeviceId();
            int i = 10;
            while (deviceId == null) {
                int i2 = i - 1;
                if (i <= 0) {
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                deviceId = telephonyManager.getDeviceId();
                i = i2;
            }
            if (deviceId != null) {
                a = deviceId;
            }
            return deviceId;
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    public static String d(Context context) {
        if (a != null) {
            return a;
        }
        try {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (deviceId == null) {
                return deviceId;
            }
            a = deviceId;
            return deviceId;
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    public static synchronized String e(Context context) {
        String str;
        synchronized (d.class) {
            if (c != null) {
                str = c;
            } else {
                str = b(context);
                str = com.xiaomi.channel.commonutils.string.d.b(str + a());
                c = str;
            }
        }
        return str;
    }

    public static String f(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    public static String g(Context context) {
        try {
            return ((WifiManager) context.getSystemService(Utility.NETWORK_WIFI)).getConnectionInfo().getMacAddress();
        } catch (Throwable e) {
            b.a(e);
            return null;
        }
    }
}
