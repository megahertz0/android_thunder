package com.xunlei.downloadprovider.homepage.choiceness.a.a;

import android.text.TextUtils;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.recommend.feed.ao;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ChoicenessInfo.java
public final class a extends e implements Comparable<a> {
    public String A;
    public long B;
    public int C;
    public int D;
    public String E;
    public String F;
    public int G;
    public String H;
    public String I;
    private int J;
    private String K;
    public boolean a;
    public int b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public int h;
    public String i;
    public String j;
    public String k;
    public List<c> l;
    public int m;
    public int n;
    public int o;
    public String p;
    public float q;
    public String r;
    public String s;
    public int t;
    public long u;
    public String v;
    public String w;
    public String x;
    public String y;
    public int z;

    public a() {
        this.s = BuildConfig.VERSION_NAME;
        this.w = "sab";
    }

    public final /* synthetic */ int compareTo(Object obj) {
        a aVar = (a) obj;
        return (aVar != null && aVar.d.equals(this.d) && aVar.c.equals(this.c)) ? 0 : -1;
    }

    public final int a() {
        return this.b;
    }

    public final boolean b() {
        return "advertisement".equals(this.c);
    }

    public final boolean c() {
        return "short_video".equals(this.c);
    }

    public final boolean d() {
        return this.b == 17;
    }

    public static a a(JSONObject jSONObject) throws JSONException {
        a aVar = new a();
        aVar.w = jSONObject.optString("s_ab");
        aVar.c = jSONObject.getString("res_type");
        aVar.b = jSONObject.getInt("display_type");
        aVar.J = jSONObject.optInt("ads_display_style");
        aVar.K = jSONObject.optString("ads_channel");
        aVar.d = jSONObject.optString("res_id");
        aVar.e = jSONObject.optString(SHubBatchQueryKeys.gcid);
        aVar.u = jSONObject.optLong(AgooConstants.MESSAGE_ID);
        aVar.i = jSONObject.optString("res_subcategories");
        aVar.j = jSONObject.optString("res_display_type");
        aVar.k = jSONObject.optString("res_title");
        JSONArray optJSONArray = jSONObject.optJSONArray("subject_list");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            aVar.m = jSONObject.optInt("subjectlist_count");
            aVar.l = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                c a = c.a(optJSONArray.getJSONObject(i));
                if (a != null) {
                    aVar.l.add(a);
                }
            }
        }
        aVar.f = jSONObject.optString("res_cover_url");
        if (jSONObject.has("poster_width")) {
            aVar.g = jSONObject.optInt("poster_width");
        }
        if (jSONObject.has("poster_height")) {
            aVar.h = jSONObject.optInt("poster_height");
        }
        aVar.n = jSONObject.optInt("play_count");
        aVar.o = jSONObject.optInt("f_count");
        aVar.p = jSONObject.optString("tag");
        aVar.q = (float) jSONObject.optDouble("douban_score");
        aVar.r = jSONObject.optString("jump_url");
        aVar.s = jSONObject.optString("introduction");
        aVar.t = jSONObject.optInt("duration");
        aVar.v = jSONObject.optString("play_url");
        aVar.x = jSONObject.optString("nickname");
        aVar.z = jSONObject.optInt("room_onlinenum");
        aVar.y = jSONObject.optString("avatar_url");
        aVar.A = jSONObject.optString("room_info");
        aVar.C = jSONObject.optInt("sign");
        aVar.D = jSONObject.optInt("grayid");
        aVar.E = jSONObject.optString("livestat");
        aVar.F = jSONObject.optString("hosttype");
        aVar.G = jSONObject.optInt("recommend");
        aVar.H = jSONObject.optString("userid");
        if (jSONObject.has("room_point")) {
            aVar.B = jSONObject.optLong("room_point");
        }
        return aVar;
    }

    public final String toString() {
        return new StringBuilder("resType=").append(this.c).append(", resId=").append(this.d).toString();
    }

    public final ao e() {
        ao aoVar = new ao();
        aoVar.n = this.j;
        aoVar.m = this.f;
        aoVar.f = this.t;
        aoVar.h = this.o;
        if (!TextUtils.isEmpty(this.d) && TextUtils.isDigitsOnly(this.d)) {
            aoVar.a = this.d;
        }
        aoVar.q = this.e;
        aoVar.c = this.v;
        aoVar.b = this.k;
        aoVar.d = this.f;
        aoVar.j = this.g;
        aoVar.k = this.h;
        return aoVar;
    }

    public final String f() {
        return this.I == null ? BuildConfig.VERSION_NAME : this.I;
    }
}
