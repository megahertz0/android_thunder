package com.inmobi.commons.a;

import android.content.Context;
import com.inmobi.commons.core.b.c;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: SdkInfo.java
public class b {
    public static String a() {
        String str = a.d;
        char[] toCharArray = c().toCharArray();
        String str2 = str;
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] == '.') {
                str2 = str2 + "T";
            } else {
                str2 = str2 + ((char) ((toCharArray[i] - 48) + 65));
            }
        }
        return new StringBuilder("pr-SAND-").append(str2).append(SocializeConstants.OP_DIVIDER_MINUS).append(f()).toString();
    }

    public static int b() {
        return XZBDevice.Predownload;
    }

    public static String c() {
        return "5.3.1";
    }

    public static String d() {
        return SocializeConstants.PROTOCOL_VERSON;
    }

    public static String e() {
        return anet.channel.strategy.dispatch.a.ANDROID;
    }

    public static String f() {
        return "20160516";
    }

    public static String g() {
        return "http://www.inmobi.com/products/sdk/#downloads";
    }

    public static String a(Context context) {
        return c.a(context, "sdk_version_store").b("sdk_version", null);
    }

    public static void a(Context context, String str) {
        c.a(context, "sdk_version_store").a("sdk_version", str);
    }

    public static boolean b(Context context) {
        return c.a(context, "sdk_version_store").b("db_deletion_failed", false);
    }

    public static void a(Context context, boolean z) {
        c.a(context, "sdk_version_store").a("db_deletion_failed", z);
    }
}
