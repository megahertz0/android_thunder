package com.xunlei.downloadprovider.model.protocol.e;

import android.content.Context;
import android.os.Handler;
import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.p;
import com.nostra13.universalimageloader.b.c;
import com.tencent.open.SocialConstants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.tdlive.WebBrowserActivity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: ShortTimeVideoManager.java
public final class a {
    private String A;
    private String B;
    private Context C;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public a(Context context) {
        this.a = "http://api-shoulei-ssl.xunlei.com/ivideo/list?type=";
        this.b = "ok";
        this.c = "result";
        this.d = "video_list";
        this.e = "movieid";
        this.f = Impl.COLUMN_GCID;
        this.g = WebBrowserActivity.EXTRA_TITLE;
        this.h = SocialConstants.PARAM_URL;
        this.i = "poster";
        this.j = "duration";
        this.k = "thumbup_count";
        this.l = "play_url";
        this.m = "channel_description";
        this.n = WebBrowserActivity.EXTRA_TITLE;
        this.o = "icon_url";
        this.p = "cover_url";
        this.q = "module_description";
        this.r = "play_count";
        this.s = "comment_num";
        this.t = "duration_sec";
        this.u = "have_favorite";
        this.v = "poster_width";
        this.w = "poster_height";
        this.x = "v_status";
        this.y = "v_url";
        this.z = "v_comment";
        this.A = "upline_time";
        this.B = "stick";
        this.C = context;
    }

    public final void a(int i, int i2, Handler handler, int i3, int i4) {
        Request aVar = new com.xunlei.downloadprovidercommon.b.a.a(this.a + i3 + "&length=" + i2 + "&offset=" + i + "&time=" + System.currentTimeMillis() + "&peerid=" + b.d(), new b(this, handler, i4), new d(this, handler, i4));
        p d = com.xunlei.downloadprovider.j.a.a().d();
        aVar.setRetryPolicy(new f(5000, 2, 1.0f));
        d.a(aVar);
    }

    final b a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        b bVar = new b();
        bVar.a = arrayList;
        if (jSONObject == null) {
            return bVar;
        }
        if (!jSONObject.has(this.c) || !jSONObject.has(this.d)) {
            return bVar;
        }
        try {
            if (!this.b.equals(jSONObject.getString(this.c))) {
                return bVar;
            }
            if (jSONObject.has(this.n)) {
                bVar.e = jSONObject.getString(this.n);
            }
            if (jSONObject.has(this.m)) {
                bVar.d = jSONObject.getString(this.m);
            }
            if (jSONObject.has(this.q)) {
                bVar.f = jSONObject.getString(this.q);
            }
            if (jSONObject.has(this.p)) {
                bVar.h = jSONObject.getString(this.p);
            }
            if (jSONObject.has(this.o)) {
                bVar.g = jSONObject.getString(this.o);
            }
            if (jSONObject.has(this.x)) {
                bVar.i = jSONObject.optInt(this.x);
            }
            if (jSONObject.has(this.y)) {
                bVar.j = jSONObject.optString(this.y);
            }
            if (jSONObject.has(this.z)) {
                bVar.k = jSONObject.optString(this.z);
            }
            JSONArray jSONArray = jSONObject.getJSONArray(this.d);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                c cVar = new c();
                if (jSONObject2.has(this.e)) {
                    cVar.a = jSONObject2.getString(this.e);
                }
                if (jSONObject2.has(this.f)) {
                    cVar.b = jSONObject2.getString(this.f);
                }
                if (jSONObject2.has(this.h)) {
                    cVar.d = jSONObject2.getString(this.h);
                }
                if (jSONObject2.has(this.g)) {
                    cVar.c = jSONObject2.getString(this.g);
                }
                if (jSONObject2.has(this.i)) {
                    cVar.e = jSONObject2.getString(this.i);
                }
                if (jSONObject2.has(this.j)) {
                    cVar.f = jSONObject2.getString(this.j);
                }
                if (jSONObject2.has(this.k)) {
                    cVar.g = jSONObject2.getInt(this.k);
                }
                if (jSONObject2.has(this.l)) {
                    cVar.i = jSONObject2.getString(this.l);
                }
                if (jSONObject2.has(this.r)) {
                    cVar.l = jSONObject2.getInt(this.r);
                }
                if (jSONObject2.has(this.s)) {
                    cVar.m = jSONObject2.getInt(this.s);
                }
                if (jSONObject2.has(this.t)) {
                    cVar.n = jSONObject2.getInt(this.t);
                }
                if (jSONObject2.has(this.v)) {
                    cVar.o = jSONObject2.getInt(this.v);
                }
                if (jSONObject2.has(this.w)) {
                    cVar.p = jSONObject2.getInt(this.w);
                }
                if (jSONObject2.has(this.u)) {
                    cVar.q = jSONObject2.getBoolean(this.u);
                }
                if (jSONObject2.has(this.A)) {
                    cVar.s = jSONObject2.getLong(this.A) * 1000;
                }
                if (jSONObject2.has(this.B)) {
                    cVar.t = jSONObject2.getInt(this.B);
                }
                arrayList.add(cVar);
            }
            return bVar;
        } catch (Throwable e) {
            c.a(e);
        }
    }
}
