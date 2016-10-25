package com.xunlei.downloadprovider.web.base;

import com.alipay.sdk.packet.d;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.SystemUtils;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;
import java.util.List;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ShortMovieDetailReporter.java
public final class bk {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static String r;
    private static String s;
    private static String t;

    static {
        a = "android_videodetail";
        b = "videoDetail_show";
        c = "videoDetail_zan";
        d = "videoDetail_discuss_zan";
        e = "videoDetail_discuss_alter";
        f = "videoDetail_discuss_show";
        g = "videoDetail_discuss_click";
        h = "videoDetail_discuss_submit";
        i = "videoDetail_discuss_result";
        j = "videoDetial_discuss_error";
        k = "videoDetail_recommend_click";
        l = "videoDetail_recommend_more_click";
        m = "videoDetail_share_click";
        n = "videoDetail_share_to";
        o = "videoDetail_share_result";
        p = "videoDetail_recommend_show";
        q = "videoDetail_common_click";
        r = "videoDetail_autoplay_next_close";
        s = "videoDetail_common_click";
        t = "videotag_click";
    }

    public static void a(String str, String str2) {
        c a = a.a(a, b);
        a.a("from", str2);
        a.a("is_play", 1);
        a.a("movieid", str);
        a(a);
    }

    public static void a(List<String> list, String str, String[] strArr) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject(strArr[2]);
            for (String str2 : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("movieid", str2);
                jSONObject2.put(d.l, jSONObject);
                jSONArray.put(jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        c a = a.a(a, p);
        a.b("from", str);
        a.b("movielist", list.toString());
        a.b("s_ab", strArr[0]);
        a.b("rec_params", jSONArray.toString());
        a.b("platformModel", strArr[3]);
        a.b(com.alipay.sdk.app.statistic.c.a, strArr[4]);
        new StringBuilder("report new result=>").append(a.a());
        com.xunlei.downloadprovidercommon.a.d.a(a);
    }

    public static void b(String str, String str2) {
        c a = a.a(a, c);
        a.a("movieid", str);
        a.a("position", str2);
        a(a);
    }

    public static void a(String str, long j, boolean z) {
        c a = a.a(a, d);
        a.a("movieid", str);
        a.a("discussid", String.valueOf(j));
        a.a(SystemUtils.IS_LOGIN, z ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
        a(a);
    }

    public static void c(String str, String str2) {
        c a = a.a(a, t);
        a.a("movieid", str);
        a.a("tag", str2);
        a(a);
    }

    public static void a(String str) {
        c a = a.a(a, e);
        a.a("to", str);
        a(a);
    }

    public static void d(String str, String str2) {
        c a = a.a(a, f);
        a.a("movieid", str);
        a.a("position", str2);
        a(a);
    }

    public static void a(long j, String str) {
        c a = a.a(a, g);
        a.a("discussid", String.valueOf(j));
        a.a("clickid", str);
        a(a);
    }

    public static void b(String str, long j, boolean z) {
        c a = a.a(a, h);
        a.a("movieid", str);
        a.a("discussid", String.valueOf(j));
        a.a("level", j == -1 ? MessageService.MSG_DB_READY_REPORT : MessageService.MSG_DB_NOTIFY_REACHED);
        a.a(SystemUtils.IS_LOGIN, z ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT);
        a(a);
    }

    public static void e(String str, String str2) {
        c a = a.a(a, j);
        a.a("discuss_type", str);
        String str3 = "errorcode";
        if ("-1001".contentEquals(str2)) {
            str2 = "network_err";
        }
        a.a(str3, str2);
        a(a);
    }

    public static void a(String str, boolean z, String str2, long j, long j2) {
        c a = a.a(a, i);
        a.a("result", z ? MsgConstant.KEY_SUCCESS : MsgConstant.KEY_FAIL);
        String str3 = "errorcode";
        if ("-1001".contentEquals(str2)) {
            str2 = "network_err";
        }
        a.a(str3, str2);
        a.a("movieid", str);
        a.a("discussid", String.valueOf(j));
        a.a("new_discussid", String.valueOf(j2));
        a.a("level", j == -1 ? MessageService.MSG_DB_READY_REPORT : MessageService.MSG_DB_NOTIFY_REACHED);
        a(a);
    }

    public static void a(String str, String str2, String[] strArr) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject(strArr[2]);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("movieid", str2);
            jSONObject2.put(d.l, jSONObject);
            jSONArray.put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        c a = a.a(a, k);
        a.a("from", "detail_shortvideo");
        a.a("movieid", str);
        a.a("click_movieid", str2);
        a.a("s_ab", strArr[0]);
        a.a("rec_params", jSONArray.toString());
        a.a("platformModel", strArr[3]);
        a.a(com.alipay.sdk.app.statistic.c.a, strArr[4]);
        a(a);
    }

    public static void b(String str) {
        c a = a.a(a, l);
        a.a("movieid", str);
        a(a);
    }

    public static void c(String str) {
        c a = a.a(a, m);
        a.a("from", str);
        a(a);
    }

    public static void a(String str, String str2, String str3) {
        c a = a.a(a, n);
        a.a("from", str3);
        a.a("to", str2);
        a.a("movieid", str);
        a.a(SocialConstants.PARAM_URL, "android_client");
        a(a);
    }

    public static void a(String str, String str2, String str3, int i, String str4) {
        c a = a.a(a, o);
        a.a("from", str4);
        a.a("to", str2);
        a.a("result", str3);
        a.a("errorcode", i);
        a.a("movieid", str);
        a.a(SocialConstants.PARAM_URL, "android_client");
        a(a);
    }

    public static void d(String str) {
        c a = a.a(a, r);
        a.a("movieid", str);
        com.xunlei.downloadprovidercommon.a.d.a(a);
    }

    private static void a(c cVar) {
        new StringBuilder("[NEW_STAT_EVENT]").append(cVar);
        com.xunlei.downloadprovidercommon.a.d.a(cVar);
    }

    public static void a(boolean z) {
        c a = a.a(a, q);
        a.a("clickid", z ? "auto_set_yes" : "auto_set_no");
        a(a);
    }

    public static void e(String str) {
        c a = a.a(a, s);
        a.a("clickid", str);
        a(a);
    }
}
