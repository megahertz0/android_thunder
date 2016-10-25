package com.xunlei.downloadprovider.search.b;

import com.sina.weibo.sdk.component.GameManager;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.e;
import com.xunlei.downloadprovider.search.a.b;
import com.xunlei.downloadprovider.search.bean.WestRankType;
import com.xunlei.downloadprovider.util.z;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

// compiled from: SearchNetworkHelper.java
public class c extends e {
    private static final String a;
    private static c b;
    private b c;
    private Map<WestRankType, Boolean> d;

    static {
        a = c.class.getSimpleName();
    }

    private c() {
        this.d = new HashMap();
        this.d.put(WestRankType.HOT_SEARCH, Boolean.valueOf(false));
        this.d.put(WestRankType.MOVIE, Boolean.valueOf(false));
        this.d.put(WestRankType.TELEPLAY, Boolean.valueOf(false));
        this.d.put(WestRankType.VARIETY, Boolean.valueOf(false));
        this.d.put(WestRankType.ANIME, Boolean.valueOf(false));
        this.c = b.a();
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public static String a(String str) {
        int i = 1;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (!LoginHelper.a().f()) {
                i = 0;
            }
            stringBuffer.append(z.a()).append("key=").append(URLEncoder.encode(str, GameManager.DEFAULT_CHARSET)).append("&type=all_suggest&pm=android&versionCode=").append(com.xunlei.downloadprovider.a.b.x()).append("&product_id=").append(com.xunlei.downloadprovider.a.b.e(BrothersApplication.a)).append("&isVip=").append(i).append(new StringBuilder("&screenWidth=").append(com.xunlei.downloadprovider.a.b.t()).toString()).append(new StringBuilder("&screenHeight=").append(com.xunlei.downloadprovider.a.b.u()).toString());
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return a.d;
        }
    }
}
