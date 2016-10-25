package com.xunlei.downloadprovider.c.a;

import com.xunlei.downloadprovider.a.b;

// compiled from: CookieConstant.java
public final class g {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final int d;

    static {
        a = b.h();
        b = b.g();
        c = b.w();
        d = b.x();
    }

    public static String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("productId=").append(a).append(";");
        stringBuilder.append("channelId=").append(b).append(";");
        stringBuilder.append("version=").append(c).append(";");
        stringBuilder.append("versionCode=").append(d);
        return stringBuilder.toString();
    }
}
