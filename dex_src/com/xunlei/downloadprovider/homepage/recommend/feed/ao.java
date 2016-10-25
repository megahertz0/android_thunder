package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.text.TextUtils;
import com.alipay.sdk.packet.d;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.c.a.f;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.WebBrowserActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: FeedVideoItemModel.java
public final class ao implements Comparable<ao> {
    public int A;
    public String B;
    private String C;
    private f D;
    public String a;
    public String b;
    public String c;
    public String d;
    public boolean e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public String l;
    public String m;
    public String n;
    String o;
    public boolean p;
    public String q;
    public long r;
    public String s;
    public String t;
    public long u;
    public String v;
    public int w;
    String x;
    public long y;
    public int z;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        ao aoVar = (ao) obj;
        return (aoVar == null || aoVar.a != this.a) ? -1 : 0;
    }

    public final String a() {
        String str = "http://h5.m.xunlei.com/h5/page/wx-share-xunlei/index.html";
        Object obj = r.c().n.c;
        String toString = new StringBuilder("??_t=").append(System.currentTimeMillis() / 300000).append("&origin=a_sl_app_v").append(b.w()).append("&id=").append(this.a).toString();
        return TextUtils.isEmpty(obj) ? str + toString : obj + toString;
    }

    public final void a(f fVar) {
        int i;
        this.D = fVar;
        if (fVar == null) {
            i = 0;
        } else {
            i = fVar.c;
        }
        this.w = i;
        if (fVar != null) {
            List list = fVar.e;
            if (list != null && list.size() > 0) {
                c cVar = (c) list.get(0);
                this.r = cVar.a;
                this.s = cVar.j;
                this.t = cVar.b;
                this.u = cVar.n;
                this.v = cVar.k;
            }
        }
    }

    public static ao a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            String string;
            int i;
            int i2;
            int optInt;
            String optString;
            String string2;
            String string3;
            String str;
            String string4 = jSONObject.getString("movieid");
            String optString2 = jSONObject.optString(Impl.COLUMN_GCID);
            String string5 = jSONObject.getString(WebBrowserActivity.EXTRA_TITLE);
            String string6 = jSONObject.getString("play_url");
            if (jSONObject.has("poster")) {
                string = jSONObject.getString("poster");
            } else {
                string = null;
            }
            boolean z = jSONObject.getBoolean("have_favorite");
            String string7 = jSONObject.getString("share_url");
            int i3 = jSONObject.getInt("duration");
            int i4 = jSONObject.getInt(JsInterface.FUNPLAY_AD_TRPE);
            int i5 = jSONObject.getInt("favorite_count");
            int optInt2 = jSONObject.optInt("play_count");
            if (jSONObject.has("poster_width")) {
                i = jSONObject.getInt("poster_width");
            } else {
                i = 0;
            }
            if (jSONObject.has("poster_height")) {
                i2 = jSONObject.getInt("poster_height");
            } else {
                i2 = 0;
            }
            if (jSONObject.has("v_status")) {
                optInt = jSONObject.optInt("v_status");
            } else {
                optInt = 0;
            }
            if (jSONObject.has("v_url")) {
                optString = jSONObject.optString("v_url");
            } else {
                optString = null;
            }
            String optString3 = jSONObject.optString("s_ab");
            String optString4 = jSONObject.optString(d.l);
            JSONObject jSONObject2 = jSONObject.getJSONObject("category_info");
            if (jSONObject2 != null) {
                string2 = jSONObject2.getString("icon_url");
                string3 = jSONObject2.getString("cover_url");
                String string8 = jSONObject2.getString(WebBrowserActivity.EXTRA_TITLE);
                str = string2;
                string2 = string3;
                string3 = string8;
            } else {
                str = null;
                string2 = null;
                string3 = null;
            }
            ao aoVar = new ao();
            try {
                aoVar.a = string4;
                aoVar.q = optString2;
                aoVar.b = string5;
                aoVar.c = string6;
                aoVar.d = string;
                aoVar.e = z;
                aoVar.C = string7;
                aoVar.f = i3;
                aoVar.g = i4;
                aoVar.h = i5;
                aoVar.i = optInt2;
                aoVar.l = str;
                aoVar.m = string2;
                aoVar.A = optInt;
                aoVar.B = optString;
                aoVar.n = string3;
                aoVar.j = i;
                aoVar.k = i2;
                aoVar.o = optString3;
                aoVar.x = optString4;
                aoVar.w = jSONObject.optInt("comment_total_num");
                if (!jSONObject.has("hot_comment")) {
                    return aoVar;
                }
                JSONObject jSONObject3 = jSONObject.getJSONObject("hot_comment");
                if (jSONObject3 == null) {
                    return aoVar;
                }
                aoVar.r = jSONObject3.optLong(SocializeConstants.WEIBO_ID);
                aoVar.s = jSONObject3.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                aoVar.t = jSONObject3.optString(ParamKey.CONTENT);
                aoVar.u = jSONObject3.optLong("nice_num");
                aoVar.v = jSONObject3.optString("avatar_url");
                return aoVar;
            } catch (JSONException e) {
                JSONException e2 = e;
                e2.printStackTrace();
                return aoVar;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            aoVar = null;
            e2 = jSONException;
            e2.printStackTrace();
            return aoVar;
        }
    }

    private static JSONObject a(ao aoVar) {
        List list = null;
        if (aoVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("movieid", aoVar.a);
            jSONObject.put(WebBrowserActivity.EXTRA_TITLE, aoVar.b);
            jSONObject.put(Impl.COLUMN_GCID, aoVar.q);
            jSONObject.put("play_url", aoVar.c);
            jSONObject.put("poster", aoVar.d);
            jSONObject.put("have_favorite", aoVar.e);
            jSONObject.put("share_url", aoVar.a());
            jSONObject.put("duration", aoVar.f);
            jSONObject.put(JsInterface.FUNPLAY_AD_TRPE, aoVar.g);
            jSONObject.put("favorite_count", aoVar.h);
            jSONObject.put("play_count", aoVar.i);
            jSONObject.put("poster_width", aoVar.j);
            jSONObject.put("poster_height", aoVar.k);
            jSONObject.put(d.l, aoVar.b());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("icon_url", aoVar.l);
            jSONObject2.put("cover_url", aoVar.m);
            jSONObject2.put(WebBrowserActivity.EXTRA_TITLE, aoVar.n);
            jSONObject.put("category_info", jSONObject2);
            jSONObject.put("comment_total_num", aoVar.w);
            jSONObject2 = new JSONObject();
            if (aoVar.D != null) {
                list = aoVar.D.e;
            }
            if (list != null && list.size() > 0) {
                c cVar = (c) list.get(0);
                jSONObject2.put(SocializeConstants.WEIBO_ID, cVar.a);
                jSONObject2.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, cVar.j);
                jSONObject2.put(ParamKey.CONTENT, cVar.b);
                jSONObject2.put("nice_num", cVar.n);
                jSONObject2.put("avatar_url", cVar.k);
                jSONObject.put("hot_comment", jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static String a(List<ao> list) {
        JSONArray b = b(list);
        return b == null ? null : b.toString();
    }

    private static JSONArray b(List<ao> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < list.size()) {
            try {
                JSONObject a = a((ao) list.get(i));
                if (a != null) {
                    jSONArray.put(i, a);
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    private static List<ao> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        List<ao> arrayList = new ArrayList();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                ao a = a(jSONArray.getJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return arrayList;
            }
        }
        return arrayList;
    }

    public static List<ao> a(String str) {
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONArray = null;
        }
        return a(jSONArray);
    }

    public final String toString() {
        return this.a + "|" + this.b;
    }

    public final String b() {
        return this.x == null ? a.d : this.x;
    }
}
