package com.xunlei.common.register.b;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLPhoneInfo;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.encrypt.SHA1;
import com.xunlei.common.register.a.c;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: XLDeviceID.java
public final class d {
    public static String a;
    private static int b;
    private static String c;
    private static String d;

    static {
        b = 101;
        a = BuildConfig.VERSION_NAME;
        d = BuildConfig.VERSION_NAME;
    }

    private static String b() {
        if (TextUtils.isEmpty(d)) {
            Object string = c.a().c().getSharedPreferences("DeviceInfo", 0).getString("device", BuildConfig.VERSION_NAME);
            XLLog.v("XLDeviceID", new StringBuilder("getLocalDeviceID = ").append(string).toString());
            if (TextUtils.isEmpty(string)) {
                String rawDeviceId = XLPhoneInfo.getRawDeviceId(c.a().c());
                XLLog.v("XLDeviceID", new StringBuilder("raw deviceid = ").append(rawDeviceId).toString());
                rawDeviceId = MD5.encrypt(rawDeviceId);
                XLLog.v("XLDeviceID", new StringBuilder("gen valid deviceid = ").append(rawDeviceId).toString());
                Editor edit = c.a().c().getSharedPreferences("DeviceInfo", 0).edit();
                edit.putString("device", rawDeviceId);
                edit.commit();
                XLLog.v("XLDeviceID", new StringBuilder("saveLocalDeviceID deviceID = ").append(rawDeviceId).toString());
                d = rawDeviceId;
            } else {
                d = string;
                XLLog.v("XLDeviceID", new StringBuilder("use local deviceid = ").append(d).toString());
            }
        } else {
            XLLog.v("XLDeviceID", new StringBuilder("use memory deviceid = ").append(d).toString());
        }
        return d;
    }

    private static String c() {
        String string = c.a().c().getSharedPreferences("DeviceInfo", 0).getString("device", BuildConfig.VERSION_NAME);
        XLLog.v("XLDeviceID", new StringBuilder("getLocalDeviceID = ").append(string).toString());
        return string;
    }

    private static void a(String str) {
        Editor edit = c.a().c().getSharedPreferences("DeviceInfo", 0).edit();
        edit.putString("device", str);
        edit.commit();
        XLLog.v("XLDeviceID", new StringBuilder("saveLocalDeviceID deviceID = ").append(str).toString());
    }

    private static String d() {
        String str = b() + c.a().h() + c.a().d() + a;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        String encrypt = SHA1.encrypt(stringBuffer.toString());
        String encrypt2 = MD5.encrypt(encrypt);
        XLLog.v("XLDeviceID", new StringBuilder("rawdata:").append(str).toString());
        XLLog.v("XLDeviceID", new StringBuilder("sha1:").append(encrypt).toString());
        XLLog.v("XLDeviceID", new StringBuilder("md5:").append(encrypt2).toString());
        return encrypt2;
    }

    public static String a() {
        StringBuilder append = new StringBuilder("div").append(b).append(".").append(b());
        String str = b() + c.a().h() + c.a().d() + a;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        String encrypt = SHA1.encrypt(stringBuffer.toString());
        String encrypt2 = MD5.encrypt(encrypt);
        XLLog.v("XLDeviceID", new StringBuilder("rawdata:").append(str).toString());
        XLLog.v("XLDeviceID", new StringBuilder("sha1:").append(encrypt).toString());
        XLLog.v("XLDeviceID", new StringBuilder("md5:").append(encrypt2).toString());
        return append.append(encrypt2).toString();
    }
}
