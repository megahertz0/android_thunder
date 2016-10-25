package com.xunlei.downloadprovider.homepage.recommend;

import com.alipay.sdk.packet.d;
import com.tencent.connect.common.Constants;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.recommend.feed.ao;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: ChannelFeedReporter.java
public final class a {
    private static List<ao> a;
    private static Set<ao> b;

    static {
        a = new ArrayList();
        b = new HashSet();
    }

    public static String a(SHARE_MEDIA share_media) {
        String str = com.umeng.a.d;
        switch (AnonymousClass_1.a[share_media.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return "wechart";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "pengyouquan";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "weibo";
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "qq";
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return Constants.SOURCE_QZONE;
            default:
                return str;
        }
    }

    public static void a(String str, String str2, String str3) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_player_click");
        a.a("clickid", str);
        a.a("movieid", str2);
        if (str3 == null) {
            str3 = com.umeng.a.d;
        }
        a.a("server", str3);
        a(a);
    }

    public static void a(String str) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_zan");
        a.a("movieid", str);
        a(a);
    }

    public static void a() {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_common_click");
        a.a("clickid", "jubao");
        a(a);
    }

    public static void b(String str) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_discuss_click");
        a.a("movieid", str);
        a(a);
    }

    public static void b(String str, String str2, String str3) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_share_to");
        a.a("position", str2);
        a.a("to", str3);
        a.a("movieid", str);
        a(a);
    }

    public static void a(String str, String str2, String str3, int i, String str4) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_share_result");
        a.a("to", str2);
        a.a("result", str3);
        a.a("errorcode", i);
        a.a("position", str4);
        a.a("movieid", str);
        a(a);
    }

    public static void a(boolean z, ao aoVar, String str, String str2) {
        if (aoVar != null) {
            if (!z || !b.contains(aoVar)) {
                Object obj;
                new StringBuilder("tryReportVideoFeedExposure--feedflow_video_show--isComplete=").append(aoVar.p).append("|id=").append(aoVar.a);
                a.add(aoVar);
                b.add(aoVar);
                if (a.size() >= 5) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    a(str, str2);
                }
            }
        }
    }

    public static void b() {
        b.clear();
    }

    public static void a(String str, String str2) {
        if (!a.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            for (ao aoVar : a) {
                stringBuilder.append(aoVar.a).append('_');
                if (aoVar.p) {
                    stringBuilder2.append(aoVar.a).append('_');
                }
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            if (stringBuilder2.length() > 0) {
                stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
            }
            String d = b.d(BrothersApplication.a());
            String a = a(a);
            c a2 = com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_video_show");
            a2.b("movielist", stringBuilder.toString()).b("movielist_finish", stringBuilder2.toString());
            a2.b("from", str);
            a2.b("channelflow_id", str2);
            a2.b("platformModel", com.xunlei.downloadprovider.a.b.q());
            a2.b(com.alipay.sdk.app.statistic.c.a, d);
            a2.b("rec_params", a);
            a(a2);
            a.clear();
        }
    }

    private static String a(Collection<ao> collection) {
        if (collection == null || collection.isEmpty()) {
            return com.umeng.a.d;
        }
        JSONArray jSONArray = new JSONArray();
        for (ao aoVar : collection) {
            Map hashMap = new HashMap();
            hashMap.put("movieid", aoVar.a);
            hashMap.put(d.l, aoVar.b());
            jSONArray.put(new JSONObject(hashMap));
        }
        return jSONArray.toString();
    }

    public static void c() {
        a(com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_notice_show"));
    }

    public static void d() {
        a(com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_notice_click"));
    }

    public static void e() {
        a(com.xunlei.downloadprovidercommon.a.a.a("android_channelflow", "channelflow_notice_close"));
    }

    private static void a(c cVar) {
        new StringBuilder("[STAT_EVENT]").append(cVar);
        com.xunlei.downloadprovidercommon.a.d.a(cVar);
    }

    public static void f() {
        if (a != null) {
            a.clear();
        }
    }
}
