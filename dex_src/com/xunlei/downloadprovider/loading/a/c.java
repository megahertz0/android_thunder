package com.xunlei.downloadprovider.loading.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

// compiled from: InmobiUtil.java
public final class c {
    private static c a;
    private Context b;

    private c(Context context) {
        this.b = context;
    }

    public static c a(Context context) {
        if (a == null) {
            a = new c(context);
        }
        return a;
    }

    public final String a() {
        String str = BuildConfig.VERSION_NAME;
        if (this.b == null) {
            return str;
        }
        TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService("phone");
        if (telephonyManager == null) {
            return str;
        }
        try {
            String deviceId = telephonyManager.getDeviceId();
            try {
                return TextUtils.isEmpty(deviceId) ? BuildConfig.VERSION_NAME : deviceId;
            } catch (Exception e) {
                e = e;
                if (e instanceof SecurityException) {
                    return deviceId;
                }
                e.printStackTrace();
                return deviceId;
            }
        } catch (Exception e2) {
            Exception exception = e2;
            deviceId = str;
            e = exception;
            Exception e3;
            if (e3 instanceof SecurityException) {
                return deviceId;
            }
            e3.printStackTrace();
            return deviceId;
        }
    }

    public static String a(String str) {
        String str2 = BuildConfig.VERSION_NAME;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.reset();
            instance.update(str.getBytes(CharsetConvert.UTF_8));
            byte[] digest = instance.digest();
            Formatter formatter = new Formatter();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                formatter.format("%02x", new Object[]{Byte.valueOf(digest[i])});
            }
            String toString = formatter.toString();
            formatter.close();
            return toString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str2;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str2;
        }
    }
}
