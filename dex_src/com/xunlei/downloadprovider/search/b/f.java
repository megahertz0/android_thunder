package com.xunlei.downloadprovider.search.b;

import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: SearchUrls.java
public abstract class f {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;

    static {
        a = a("http://m.sjzhushou.com/cgi-bin/baidureci?tab=hotspot_random");
        b = a("http://m.sjzhushou.com/cgi-bin/baidureci?tab=movie");
        c = a("http://m.sjzhushou.com/cgi-bin/baidureci?tab=teleplay");
        d = a("http://m.sjzhushou.com/cgi-bin/baidureci?tab=variety");
        e = a("http://m.sjzhushou.com/cgi-bin/baidureci?tab=anime");
    }

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append("&ver=v2&peer_id=").append(b.d()).append("&product_id=").append(b.h()).append("&version=").append(b.x()).append("&userid=").append(LoginHelper.a().j).append("&imei=").append(b.f()).append("&isVip=").append(LoginHelper.a().z).append("&screenWidth=").append(b.t()).append("&screenHeight=").append(b.u());
        return stringBuilder.toString();
    }
}
