package com.xunlei.downloadprovider.homepage.recommend;

import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;

public final class WifiPopWindowReporter {

    public enum EventType {
        android_feedflow,
        android_videodetail;

        static {
            android_feedflow = new com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter.EventType("android_feedflow", 0);
            android_videodetail = new com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter.EventType("android_videodetail", 1);
            a = new com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter.EventType[]{android_feedflow, android_videodetail};
        }
    }

    public enum PageFrom {
        FEED_FLOW,
        VIDEO_DEITAIL;

        static {
            FEED_FLOW = new com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter.PageFrom("FEED_FLOW", 0);
            VIDEO_DEITAIL = new com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter.PageFrom("VIDEO_DEITAIL", 1);
            a = new com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter.PageFrom[]{FEED_FLOW, VIDEO_DEITAIL};
        }
    }

    public static void a(PageFrom pageFrom, String str, String str2) {
        String name;
        String str3 = null;
        if (pageFrom == PageFrom.FEED_FLOW) {
            name = EventType.android_feedflow.name();
            str3 = "feedflow_net_pop";
        } else if (pageFrom == PageFrom.VIDEO_DEITAIL) {
            name = EventType.android_videodetail.name();
            str3 = "videodetail_net_pop";
        } else {
            name = null;
        }
        c a = a.a(name, str3);
        a.b("net_typ", str);
        a.b("movieid", str2);
        a(a);
    }

    public static void a(PageFrom pageFrom, String str, String str2, int i) {
        String name;
        String str3 = null;
        if (pageFrom == PageFrom.FEED_FLOW) {
            name = EventType.android_feedflow.name();
            str3 = "feedflow_net_click";
        } else if (pageFrom == PageFrom.VIDEO_DEITAIL) {
            name = EventType.android_videodetail.name();
            str3 = "videodetail_net_click";
        } else {
            name = null;
        }
        c a = a.a(name, str3);
        a.b("net_typ", str);
        a.b("movieid", str2);
        a.b("clickid", String.valueOf(i));
        a(a);
    }

    private static void a(c cVar) {
        String str;
        StringBuilder stringBuilder = new StringBuilder("[STAT_EVENT]");
        if (cVar == null) {
            str = "hubbleData is null";
        } else {
            str = new StringBuilder("StatFields =").append(cVar.a()).toString();
        }
        stringBuilder.append(str);
        d.a(cVar);
    }
}
