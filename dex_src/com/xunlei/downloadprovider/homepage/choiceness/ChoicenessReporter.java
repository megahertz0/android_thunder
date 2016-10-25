package com.xunlei.downloadprovider.homepage.choiceness;

import android.text.TextUtils;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import com.xunlei.xllib.a.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ChoicenessReporter {
    private static List<a> a;
    private static Set<a> b;

    public enum RefreshType {
        single_click_bottom_rec,
        single_click_top_tab,
        double_click_top_tab,
        auto_pull,
        manul_pull;

        static {
            single_click_bottom_rec = new com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType("single_click_bottom_rec", 0);
            single_click_top_tab = new com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType("single_click_top_tab", 1);
            double_click_top_tab = new com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType("double_click_top_tab", 2);
            auto_pull = new com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType("auto_pull", 3);
            manul_pull = new com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType("manul_pull", 4);
            a = new com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter.RefreshType[]{single_click_bottom_rec, single_click_top_tab, double_click_top_tab, auto_pull, manul_pull};
        }
    }

    static {
        a = new ArrayList();
        b = new HashSet();
    }

    public static void a(int i, boolean z, a aVar) {
        if (aVar != null && !aVar.b()) {
            if (aVar.d()) {
                c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_collect_caomei_show");
                a.a("rn", i);
                a.a("hostid", aVar.H);
                a.a("viewernum", aVar.z);
                a.a("grayid", aVar.D);
                a.a("hosttype", aVar.F);
                a.a("recommend", aVar.G);
                a.a("sign", aVar.C);
                a.a("livestat", aVar.E);
                a.a("roomid", aVar.B);
                a(a);
            }
            a(z, aVar);
        }
    }

    public static void a(boolean z, a aVar) {
        if (aVar != null && !aVar.b()) {
            if (!z || !b.contains(aVar)) {
                Object obj;
                new StringBuilder("tryReportChoicenessExposure--home_collect_content_show--id=").append(aVar.d);
                a.add(aVar);
                b.add(aVar);
                if (a.size() >= 5) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    a();
                }
            }
        }
    }

    public static void a() {
        if (!a.isEmpty()) {
            String str;
            c a = a("home_collect_content_show", a);
            String d = b.d(BrothersApplication.a());
            Collection<a> collection = a;
            if (collection == null || collection.isEmpty()) {
                str = BuildConfig.VERSION_NAME;
            } else {
                JSONArray jSONArray = new JSONArray();
                for (a aVar : collection) {
                    Map hashMap = new HashMap();
                    hashMap.put("movieid", aVar.d);
                    hashMap.put("params", aVar.f());
                    hashMap.put("format_type", String.valueOf(aVar.b));
                    jSONArray.put(new JSONObject(hashMap));
                }
                str = jSONArray.toString();
            }
            a.a("rec_params", str);
            a.b("platformModel", com.xunlei.downloadprovider.a.b.q());
            a.b("net", d);
            a(a);
            a.clear();
        }
    }

    public static void b() {
        b.clear();
    }

    public static void c() {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_collect_refresh_request");
        a.a("refresh_type", "push");
        a(a);
    }

    public static void a(RefreshType refreshType) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_collect_refresh_request");
        a.a("refresh_type", refreshType.name());
        a.a("nettype", b.d(BrothersApplication.a));
        a(a);
    }

    public static void a(RefreshType refreshType, int i) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_collect_refresh_fail");
        a.a("refresh_type", refreshType.name());
        a.a("nettype", b.d(BrothersApplication.a));
        a.a("errorcode", i);
        a(a);
    }

    public static void a(RefreshType refreshType, List<a> list) {
        c a = a("home_collect_refresh", (Collection) list);
        a.a("refresh_type", refreshType.name());
        a(a);
    }

    private static c a(String str, Collection<a> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        StringBuilder stringBuilder4 = new StringBuilder();
        StringBuilder stringBuilder5 = new StringBuilder();
        StringBuilder stringBuilder6 = new StringBuilder();
        StringBuilder stringBuilder7 = new StringBuilder();
        StringBuilder stringBuilder8 = new StringBuilder();
        StringBuilder stringBuilder9 = new StringBuilder();
        StringBuilder stringBuilder10 = new StringBuilder();
        StringBuilder stringBuilder11 = new StringBuilder();
        StringBuilder stringBuilder12 = new StringBuilder();
        if (collection != null) {
            for (a aVar : collection) {
                switch (aVar.b) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        stringBuilder3.append(aVar.d).append('_');
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        stringBuilder2.append(aVar.d).append('_');
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        stringBuilder.append(aVar.d).append('_');
                        break;
                    case SimpleLog.LOG_LEVEL_OFF:
                        stringBuilder4.append(aVar.d).append('_');
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_ADASH:
                        stringBuilder5.append(aVar.d).append('_');
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_OPEN:
                        stringBuilder7.append(aVar.d).append('_');
                        break;
                    case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                        stringBuilder6.append(aVar.d).append('_');
                        break;
                    case R.styleable.Toolbar_titleMargins:
                        stringBuilder9.append(aVar.d).append('_');
                        break;
                    case R.styleable.Toolbar_titleMarginStart:
                        stringBuilder8.append(aVar.d).append('_');
                        break;
                    case R.styleable.Toolbar_titleMarginEnd:
                        stringBuilder10.append(aVar.d).append('_');
                        break;
                    case R.styleable.Toolbar_titleMarginTop:
                        stringBuilder11.append(aVar.d).append('_');
                        break;
                    case R.styleable.Toolbar_collapseIcon:
                        stringBuilder12.append(aVar.d).append('_');
                        break;
                    default:
                        break;
                }
            }
            a(stringBuilder);
            a(stringBuilder2);
            a(stringBuilder3);
            a(stringBuilder4);
            a(stringBuilder5);
            a(stringBuilder6);
            a(stringBuilder7);
            a(stringBuilder8);
            a(stringBuilder9);
            a(stringBuilder);
            a(stringBuilder10);
            a(stringBuilder11);
            a(stringBuilder12);
        }
        return com.xunlei.downloadprovidercommon.a.a.a("android_hometab", str).a("kandan_list", stringBuilder.toString()).a("video_list", stringBuilder3.toString()).a("video_new1_list", stringBuilder4.toString()).a("video_new2_list", stringBuilder5.toString()).a("yingshi_list", stringBuilder2.toString()).a("video_gif1_list", stringBuilder6.toString()).a("video_gif2_list", stringBuilder7.toString()).a("vip1_list", stringBuilder8.toString()).a("vip2_list", stringBuilder9.toString()).a("video_mini1_list", stringBuilder10.toString()).a("video_mini2_list", stringBuilder11.toString()).a("video_autoplay", stringBuilder12.toString());
    }

    private static void a(StringBuilder stringBuilder) {
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    public static void d() {
        a(com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_choose_click"));
    }

    public static void e() {
        a(com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_choose_close_click"));
    }

    public static void a(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer num : list) {
            stringBuilder.append(num).append('_');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_choose_suc_click");
        a.a("word_list", stringBuilder.toString());
        a(a);
    }

    public static void a(String str) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_topbar_show");
        a.a(AgooConstants.MESSAGE_TYPE, str);
        a(a);
    }

    public static void a(String str, String str2) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_topbar_click");
        a.a(AgooConstants.MESSAGE_TYPE, str);
        a.a("clickid", str2);
        a(a);
    }

    public static void a(int i, a aVar) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_collect_caomei_click");
        a.a("rn", i);
        a.a("hostid", aVar.H);
        a.a("viewernum", aVar.z);
        a.a("grayid", aVar.D);
        a.a("hosttype", aVar.F);
        a.a("recommend", aVar.G);
        a.a("sign", aVar.C);
        a.a("livestat", aVar.E);
        a.a("roomid", aVar.B);
        a(a);
    }

    public static void a(boolean z) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_caomeitab_click");
        a.a("status", z ? "point" : "0");
        a(a);
    }

    private static void a(c cVar) {
        new StringBuilder("[STAT_EVENT]").append(cVar);
        d.a(cVar);
    }

    public static void a(String str, int i, String str2, String str3) {
        Object obj = null;
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                obj = WeiXinShareContent.TYPE_VIDEO;
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                obj = "yingshi";
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                obj = "kandan_3";
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                obj = "kandan_1";
                break;
            case SimpleLog.LOG_LEVEL_OFF:
                obj = "video_new1";
                break;
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                obj = "video_new2";
                break;
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                obj = "video_gif2_list";
                break;
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                obj = "video_gif1_list";
                break;
            case R.styleable.Toolbar_titleMargins:
                obj = "vip_activity2";
                break;
            case R.styleable.Toolbar_titleMarginStart:
                obj = "vip_activity1";
                break;
            case R.styleable.Toolbar_titleMarginEnd:
                obj = "video_mini1_list";
                break;
            case R.styleable.Toolbar_titleMarginTop:
                obj = "video_mini2_list";
                break;
            case R.styleable.Toolbar_collapseIcon:
                obj = "video_autoplay";
                break;
        }
        if (!TextUtils.isEmpty(obj)) {
            a(com.xunlei.downloadprovidercommon.a.a.a("android_hometab", "home_collect_click").a(AgooConstants.MESSAGE_ID, str).a("format_type", obj).a("areaid", str2).a("platformModel", com.xunlei.downloadprovider.a.b.q()).a("net", b.d(BrothersApplication.a())).a("rec_params", str3));
        }
    }
}
