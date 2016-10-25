package com.xunlei.downloadprovider.web.base.model;

import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.WebBrowserActivity;
import java.io.Serializable;
import java.util.HashMap;
import org.json.JSONObject;

// compiled from: ShortMovieInfo.java
public final class u implements Serializable {
    private static final HashMap<String, String> w;
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public long o;
    public String p;
    public String q;
    public int r;
    public String s;
    public String t;
    private String u;
    private String v;

    static {
        w = new v();
    }

    public final String a() {
        String str = "http://h5.m.xunlei.com/h5/page/wx-share-xunlei/index.html";
        Object obj = r.c().n.c;
        String toString = new StringBuilder("?_t=").append(System.currentTimeMillis() / 300000).append("&origin=a_sl_app_v").append(b.w()).append("&id=").append(this.a).toString();
        return TextUtils.isEmpty(obj) ? str + toString : obj + toString;
    }

    public static u a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return null;
        }
        u uVar = new u();
        uVar.b = jSONObject.optString(WebBrowserActivity.EXTRA_TITLE);
        uVar.a = jSONObject.optString("movieid");
        uVar.g = jSONObject.optString(Impl.COLUMN_GCID);
        uVar.d = jSONObject.optString("duration");
        uVar.o = jSONObject.optLong("duration_sec", 0);
        uVar.l = jSONObject.optInt("thumbup_count", 0);
        uVar.m = jSONObject.optInt("play_count", 0);
        uVar.v = jSONObject.optString(SocialConstants.PARAM_SOURCE);
        uVar.v = (String) w.get(uVar.v);
        uVar.c = jSONObject.optString("poster");
        uVar.u = jSONObject.optString(SocialConstants.PARAM_URL);
        uVar.e = jSONObject.optString("play_url");
        uVar.k = jSONObject.optBoolean("have_favorite");
        uVar.n = jSONObject.optInt(JsInterface.FUNPLAY_AD_TRPE);
        uVar.i = jSONObject.optString("channel_title");
        uVar.h = jSONObject.optString("channel_icon_url");
        uVar.j = jSONObject.optString("channel_poster_url");
        uVar.r = jSONObject.optInt("v_status", 0);
        uVar.s = jSONObject.optString("v_url", a.d);
        if (TextUtils.isEmpty(uVar.e)) {
            uVar.e = uVar.u;
        }
        return uVar;
    }
}
