package com.xunlei.common.member.c;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alipay.sdk.packet.d;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLPhoneInfo;
import com.xunlei.common.encrypt.MD5;
import com.xunlei.common.encrypt.SHA1;
import com.xunlei.common.member.a.m;

// compiled from: XLDeviceID.java
public final class v {
    public static String a;
    private static String b;
    private static String c;

    static {
        a = a.d;
        c = a.d;
    }

    public static String a() {
        if (TextUtils.isEmpty(c)) {
            Object string = m.a().h().getSharedPreferences("DeviceInfo", 0).getString(d.n, a.d);
            XLLog.v("XLDeviceID", new StringBuilder("getLocalDeviceID = ").append(string).toString());
            if (TextUtils.isEmpty(string)) {
                String rawDeviceId = XLPhoneInfo.getRawDeviceId(m.a().h());
                XLLog.v("XLDeviceID", new StringBuilder("raw deviceid = ").append(rawDeviceId).toString());
                rawDeviceId = MD5.encrypt(rawDeviceId);
                XLLog.v("XLDeviceID", new StringBuilder("gen valid deviceid = ").append(rawDeviceId).toString());
                Editor edit = m.a().h().getSharedPreferences("DeviceInfo", 0).edit();
                edit.putString(d.n, rawDeviceId);
                edit.commit();
                XLLog.v("XLDeviceID", new StringBuilder("saveLocalDeviceID deviceID = ").append(rawDeviceId).toString());
                c = rawDeviceId;
            } else {
                c = string;
                XLLog.v("XLDeviceID", new StringBuilder("use local deviceid = ").append(c).toString());
            }
        } else {
            XLLog.v("XLDeviceID", new StringBuilder("use memory deviceid = ").append(c).toString());
        }
        return c;
    }

    private static String c() {
        String string = m.a().h().getSharedPreferences("DeviceInfo", 0).getString(d.n, a.d);
        XLLog.v("XLDeviceID", new StringBuilder("getLocalDeviceID = ").append(string).toString());
        return string;
    }

    private static void a(String str) {
        Editor edit = m.a().h().getSharedPreferences("DeviceInfo", 0).edit();
        edit.putString(d.n, str);
        edit.commit();
        XLLog.v("XLDeviceID", new StringBuilder("saveLocalDeviceID deviceID = ").append(str).toString());
    }

    private static String d() {
        String str = a() + m.a().m() + m.a().d() + a;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        String encrypt = SHA1.encrypt(stringBuffer.toString());
        String encrypt2 = MD5.encrypt(encrypt);
        XLLog.v("XLDeviceID", new StringBuilder("rawdata:").append(str).toString());
        XLLog.v("XLDeviceID", new StringBuilder("sha1:").append(encrypt).toString());
        XLLog.v("XLDeviceID", new StringBuilder("md5:").append(encrypt2).toString());
        return encrypt2;
    }

    public static String b() {
        StringBuilder append = new StringBuilder("div101.").append(a());
        String str = a() + m.a().m() + m.a().d() + a;
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
