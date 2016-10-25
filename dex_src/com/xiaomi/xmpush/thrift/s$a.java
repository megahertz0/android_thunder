package com.xiaomi.xmpush.thrift;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.android.agoo.common.AgooConstants;

public enum s$a {
    DEBUG((short) 1, "debug"),
    TARGET((short) 2, "target"),
    ID((short) 3, AgooConstants.MESSAGE_ID),
    APP_ID((short) 4, "appId"),
    APP_VERSION((short) 5, "appVersion"),
    PACKAGE_NAME((short) 6, "packageName"),
    TOKEN((short) 7, "token"),
    DEVICE_ID((short) 8, "deviceId"),
    ALIAS_NAME((short) 9, "aliasName"),
    SDK_VERSION((short) 10, "sdkVersion"),
    REG_ID((short) 11, "regId"),
    PUSH_SDK_VERSION_NAME((short) 12, "pushSdkVersionName"),
    PUSH_SDK_VERSION_CODE((short) 13, "pushSdkVersionCode"),
    APP_VERSION_CODE((short) 14, "appVersionCode"),
    ANDROID_ID((short) 15, "androidId"),
    IMEI((short) 16, SocializeProtocolConstants.PROTOCOL_KEY_IMEI),
    SERIAL((short) 17, "serial"),
    IMEI_MD5((short) 18, "imeiMd5"),
    SPACE_ID((short) 19, "spaceId"),
    CONNECTION_ATTRS((short) 100, "connectionAttrs"),
    CLEAN_OLD_REG_INFO((short) 101, "cleanOldRegInfo"),
    OLD_REG_ID((short) 102, "oldRegId");
    private static final Map<String, s$a> w;
    private final short x;
    private final String y;

    static {
        String str = "debug";
        a = new s$a("DEBUG", 0, (short) 1, "debug");
        String str2 = "target";
        b = new s$a("TARGET", 1, (short) 2, "target");
        str2 = AgooConstants.MESSAGE_ID;
        c = new s$a("ID", 2, (short) 3, AgooConstants.MESSAGE_ID);
        str2 = "appId";
        d = new s$a("APP_ID", 3, (short) 4, "appId");
        str2 = "appVersion";
        e = new s$a("APP_VERSION", 4, (short) 5, "appVersion");
        str = "packageName";
        f = new s$a("PACKAGE_NAME", 5, (short) 6, "packageName");
        String str3 = "token";
        g = new s$a("TOKEN", 6, (short) 7, "token");
        str3 = "deviceId";
        h = new s$a("DEVICE_ID", 7, (short) 8, "deviceId");
        str3 = "aliasName";
        i = new s$a("ALIAS_NAME", 8, (short) 9, "aliasName");
        str3 = "sdkVersion";
        j = new s$a("SDK_VERSION", 9, (short) 10, "sdkVersion");
        str3 = "regId";
        k = new s$a("REG_ID", 10, (short) 11, "regId");
        str3 = "pushSdkVersionName";
        l = new s$a("PUSH_SDK_VERSION_NAME", 11, (short) 12, "pushSdkVersionName");
        str3 = "pushSdkVersionCode";
        m = new s$a("PUSH_SDK_VERSION_CODE", 12, (short) 13, "pushSdkVersionCode");
        str3 = "appVersionCode";
        n = new s$a("APP_VERSION_CODE", 13, (short) 14, "appVersionCode");
        str3 = "androidId";
        o = new s$a("ANDROID_ID", 14, (short) 15, "androidId");
        str3 = SocializeProtocolConstants.PROTOCOL_KEY_IMEI;
        p = new s$a("IMEI", 15, (short) 16, SocializeProtocolConstants.PROTOCOL_KEY_IMEI);
        str3 = "serial";
        q = new s$a("SERIAL", 16, (short) 17, "serial");
        str3 = "imeiMd5";
        r = new s$a("IMEI_MD5", 17, (short) 18, "imeiMd5");
        str3 = "spaceId";
        s = new s$a("SPACE_ID", 18, (short) 19, "spaceId");
        str3 = "connectionAttrs";
        t = new s$a("CONNECTION_ATTRS", 19, (short) 100, "connectionAttrs");
        str3 = "cleanOldRegInfo";
        u = new s$a("CLEAN_OLD_REG_INFO", 20, (short) 101, "cleanOldRegInfo");
        str3 = "oldRegId";
        v = new s$a("OLD_REG_ID", 21, (short) 102, "oldRegId");
        z = new s$a[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v};
        w = new HashMap();
        Iterator it = EnumSet.allOf(s$a.class).iterator();
        while (it.hasNext()) {
            s$a com_xiaomi_xmpush_thrift_s_a = (s$a) it.next();
            w.put(com_xiaomi_xmpush_thrift_s_a.a(), com_xiaomi_xmpush_thrift_s_a);
        }
    }

    private s$a(short s, String str) {
        this.x = s;
        this.y = str;
    }

    public final String a() {
        return this.y;
    }
}
