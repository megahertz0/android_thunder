package com.xunlei.downloadprovider.homepage.recommend;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.recommend.feed.ao;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.a.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONArray;
import org.json.JSONObject;

public final class VideoFeedReporter {
    private static List<ao> a;
    private static Set<ao> b;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[SHARE_MEDIA.values().length];
            try {
                a[SHARE_MEDIA.WEIXIN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[SHARE_MEDIA.WEIXIN_CIRCLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[SHARE_MEDIA.SINA.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[SHARE_MEDIA.QQ.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[SHARE_MEDIA.QZONE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum RefreshType {
        single_click_bottom_rec,
        double_click_top_tab,
        single_click_top_tab,
        auto_pull,
        manul_pull;

        static {
            single_click_bottom_rec = new com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType("single_click_bottom_rec", 0);
            double_click_top_tab = new com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType("double_click_top_tab", 1);
            single_click_top_tab = new com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType("single_click_top_tab", 2);
            auto_pull = new com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType("auto_pull", 3);
            manul_pull = new com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType("manul_pull", 4);
            a = new com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType[]{single_click_bottom_rec, double_click_top_tab, single_click_top_tab, auto_pull, manul_pull};
        }
    }

    static {
        a = new ArrayList();
        b = new HashSet();
    }

    public static String a(SHARE_MEDIA share_media) {
        String str = BuildConfig.VERSION_NAME;
        switch (AnonymousClass_1.a[share_media.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return "wechart";
            case SimpleLog.LOG_LEVEL_DEBUG:
                return "pengyouquan";
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return "weibo";
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return "qq";
            case SimpleLog.LOG_LEVEL_ERROR:
                return "qzone";
            default:
                return str;
        }
    }

    public static void a(RefreshType refreshType, String str) {
        c a = a.a("android_feedflow", "feedflow_refresh");
        a.a(AgooConstants.MESSAGE_TYPE, refreshType.name());
        a.a("movielist", str);
        a(a);
    }

    public static void a(RefreshType refreshType) {
        c a = a.a("android_feedflow", "feedflow_refresh_request");
        a.a(AgooConstants.MESSAGE_TYPE, refreshType.name());
        a.a("nettype", b.d(BrothersApplication.a));
        a(a);
    }

    public static void a(RefreshType refreshType, int i) {
        c a = a.a("android_feedflow", "feedflow_refresh_fail");
        a.a(AgooConstants.MESSAGE_TYPE, refreshType.name());
        a.a("nettype", b.d(BrothersApplication.a));
        a.a("errorcode", i);
        a(a);
    }

    public static void a(String str, String str2, String str3, String str4) {
        c a = a.a("android_feedflow", "feedflow_player_click");
        a.b("clickid", str);
        a.b("movieid", str2);
        if (str3 == null) {
            str3 = BuildConfig.VERSION_NAME;
        }
        a.b("server", str3);
        a.b("rec_params", str4);
        a.b("platformModel", com.xunlei.downloadprovider.a.b.q());
        a.b("net", b.d(BrothersApplication.a()));
        a(a);
    }

    public static void a(String str) {
        c a = a.a("android_feedflow", "feedflow_zan");
        a.a("movieid", str);
        a(a);
    }

    public static void a() {
        c a = a.a("android_feedflow", "feedflow_common_click");
        a.a("clickid", "jubao");
        a(a);
    }

    public static void b(String str) {
        c a = a.a("android_feedflow", "feedflow_discuss_click");
        a.a("movieid", str);
        a(a);
    }

    public static void c(String str) {
        c a = a.a("android_feedflow", "feedflow_share_show");
        a.a("movieid", str);
        a(a);
    }

    public static void a(String str, String str2) {
        c a = a.a("android_feedflow", "feedflow_share_click");
        a.a("position", str2);
        a.a("movieid", str);
        a(a);
    }

    public static void a(String str, String str2, String str3) {
        c a = a.a("android_feedflow", "feedflow_share_to");
        a.a("position", str2);
        a.a(SocializeProtocolConstants.PROTOCOL_KEY_TO, str3);
        a.a("movieid", str);
        a(a);
    }

    public static void a(String str, String str2, String str3, int i, String str4) {
        c a = a.a("android_feedflow", "feedflow_share_result");
        a.a(SocializeProtocolConstants.PROTOCOL_KEY_TO, str2);
        a.a("result", str3);
        a.a("errorcode", i);
        a.a("position", str4);
        a.a("movieid", str);
        a(a);
    }

    public static void a(boolean z, ao aoVar) {
        Object obj = 1;
        if (aoVar != null) {
            if (!z || !b.contains(aoVar)) {
                Object obj2;
                new StringBuilder("tryReportVideoFeedExposure--feedflow_video_show--isComplete=").append(aoVar.p).append("|id=").append(aoVar.a);
                a.add(aoVar);
                b.add(aoVar);
                if (a.size() >= 5) {
                    int i = 1;
                } else {
                    obj2 = null;
                }
                if (obj2 != null) {
                    c();
                }
                String valueOf = String.valueOf(aoVar.a);
                if (aoVar.t == null) {
                    obj = null;
                }
                if (obj != null) {
                    c a = a.a("android_feedflow", "feedflow_hot_show");
                    a.b("movieid", valueOf);
                    a(a);
                }
            }
        }
    }

    public static void b() {
        b.clear();
    }

    public static void c() {
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
            c a2 = a.a("android_feedflow", "feedflow_video_show");
            a2.b("movielist", stringBuilder.toString()).b("movielist_finish", stringBuilder2.toString());
            a2.b("platformModel", com.xunlei.downloadprovider.a.b.q());
            a2.b("net", d);
            a2.b("rec_params", a);
            a(a2);
            a.clear();
        }
    }

    private static String a(Collection<ao> collection) {
        if (collection == null || collection.isEmpty()) {
            return BuildConfig.VERSION_NAME;
        }
        JSONArray jSONArray = new JSONArray();
        for (ao aoVar : collection) {
            Map hashMap = new HashMap();
            hashMap.put("movieid", String.valueOf(aoVar.a));
            hashMap.put("params", aoVar.b());
            jSONArray.put(new JSONObject(hashMap));
        }
        return jSONArray.toString();
    }

    public static String a(List<ao> list) {
        if (list == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (ao aoVar : list) {
            if (aoVar == null) {
                return null;
            }
            stringBuilder.append(aoVar.a).append('_');
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public static void a(String str, long j) {
        c a = a.a("android_feedflow", "feedflow_hot_discuss_click");
        a.a("movieid", str);
        a.a("discussid", j);
        a(a);
    }

    public static void d() {
        a(a.a("android_feedflow", "feedflow_channelflow_notice_show"));
    }

    public static void e() {
        a(a.a("android_feedflow", "feedflow_channelflow_notice_click"));
    }

    public static void f() {
        a(a.a("android_feedflow", "feedflow_channelflow_notice_close"));
    }

    private static void a(c cVar) {
        new StringBuilder("[STAT_EVENT]").append(cVar);
        d.a(cVar);
    }
}
