package com.xunlei.downloadprovider.ad.home;

import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;
import com.xunlei.xllib.a.b;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;

// compiled from: HomeFeedReporter.java
public final class a {
    public static void a(String str, Map<String, String> map) {
        a(str, map, false);
    }

    public static void a(String str, Map<String, String> map, boolean z) {
        c b = c.a("android_advertise").b("attribute1", str);
        if (map != null) {
            for (String c : map.keySet()) {
                String c2;
                b.b(c2, (String) map.get(c2));
            }
        }
        if (z) {
            String str2 = "net_type";
            c2 = b.c(BrothersApplication.a());
            if (c2 != null && c2.equals("null")) {
                c2 = "0";
            }
            b.b(str2, c2);
        }
        d.a(b);
    }

    public static void a(String str, String str2, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put(AgooConstants.MESSAGE_ID, str);
        hashMap.put("ad_type", str2);
        hashMap.put("ad_content", str3);
        hashMap.put("material", str4);
        a("adv_homeflow_bigvideo_show", hashMap);
    }

    public static void a(String str, String str2, String str3, String str4, String str5) {
        Map hashMap = new HashMap();
        hashMap.put(AgooConstants.MESSAGE_ID, str);
        hashMap.put("ad_type", str2);
        hashMap.put("ad_content", str3);
        hashMap.put("position", str4);
        hashMap.put("material", str5);
        a("adv_homeflow_bigvideo_click", hashMap);
    }
}
