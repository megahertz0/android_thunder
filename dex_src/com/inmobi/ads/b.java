package com.inmobi.ads;

import android.graphics.Color;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.tencent.open.SocialConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: AdConfig.java
public final class b extends com.inmobi.commons.core.configs.a {
    private static final String a;
    private static final String b;
    private static final Object c;
    private String d;
    private int e;
    private int f;
    private int g;
    private a h;
    private Map<String, a> i;
    private b j;
    private e k;
    private c l;
    private f m;
    private JSONObject n;
    private d o;

    // compiled from: AdConfig.java
    static final class a {
        private int a;
        private int b;
        private int c;
        private long d;

        a() {
        }

        public final boolean a() {
            return this.b > 0 && this.a >= 0 && this.c >= 0 && this.d >= 0;
        }

        public final int b() {
            return this.a;
        }

        public final int c() {
            return this.b;
        }

        public final int d() {
            return this.c;
        }

        public final long e() {
            return this.d;
        }
    }

    // compiled from: AdConfig.java
    public static final class b {
        private int a;
        private int b;
        private int c;
        private int d;
        private int e;
        private long f;

        public b() {
            this.a = 3;
            this.b = 60;
            this.c = 120;
            this.d = 500;
            this.e = 10;
            this.f = 10800;
        }

        public final int a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final int d() {
            return this.d;
        }

        public final int e() {
            return this.e;
        }

        public final long f() {
            return this.f;
        }
    }

    // compiled from: AdConfig.java
    public static final class c {
        private long a;
        private int b;
        private int c;
        private String d;

        public c() {
            this.a = 432000;
            this.b = 3;
            this.c = 60;
            this.d = "https://i.l.inmobicdn.net/sdk/sdk/500/android/mraid.js";
        }

        public final long a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final String d() {
            return this.d;
        }
    }

    // compiled from: AdConfig.java
    public static final class d {
        private boolean a;
        private long b;
        private int c;

        public d() {
            this.a = false;
            this.b = 259200;
            this.c = 5;
        }

        public final long a() {
            return this.b;
        }

        public final boolean b() {
            return this.a;
        }

        public final int c() {
            return this.c;
        }
    }

    // compiled from: AdConfig.java
    public static final class e {
        private int a;
        private int b;
        private int c;
        private int d;
        private String e;
        private int f;
        private int g;
        private int h;
        private long i;
        private ArrayList<String> j;

        public e() {
            this.a = 60;
            this.b = 320;
            this.c = 480;
            this.d = 100;
            this.e = "#00000000";
            this.f = Color.parseColor("#00000000");
            this.g = 5;
            this.h = 20;
            this.i = 5242880;
            this.j = new ArrayList(Arrays.asList(new String[]{"video/mp4"}));
        }

        public final int a() {
            return this.b;
        }

        public final int b() {
            return this.c;
        }

        public final int c() {
            return this.d;
        }

        public final int d() {
            return this.f;
        }

        public final int e() {
            return this.g;
        }

        public final int f() {
            return this.h;
        }

        public final long g() {
            return this.i;
        }

        public final List<String> h() {
            return this.j;
        }

        public final int i() {
            return this.a;
        }
    }

    // compiled from: AdConfig.java
    public static final class f {
        private int a;
        private int b;
        private int c;
        private int d;

        public f() {
            this.a = 50;
            this.b = 1000;
            this.c = 100;
            this.d = 250;
        }

        public final int a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final int d() {
            return this.d;
        }
    }

    static {
        a = b.class.getSimpleName();
        "production".equals("staging");
        b = "http://i.w.inmobi.com/showad.asm";
        c = new Object();
    }

    private JSONObject p() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", 0);
        jSONObject2.put("fetchLimit", 1);
        jSONObject2.put("minThreshold", 0);
        jSONObject2.put("timeToLive", 3300);
        jSONObject.put("base", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", R.styleable.AppCompatTheme_buttonStyle);
        jSONObject2.put("fetchLimit", XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        jSONObject2.put("minThreshold", XZBDevice.DOWNLOAD_LIST_RECYCLE);
        jSONObject2.put("timeToLive", 3300);
        jSONObject.put("native", jSONObject2);
        return jSONObject;
    }

    private JSONObject q() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", false);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", false);
        return jSONObject;
    }

    public b() {
        this.d = b;
        this.e = 20;
        this.f = 60;
        this.g = 60;
        this.j = new b();
        this.k = new e();
        this.l = new c();
        this.m = new f();
        this.o = new d();
        try {
            b(p());
            this.n = q();
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Default config provided for ads is invalid.", e);
        }
    }

    public final String a() {
        return "ads";
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        if (jSONObject.has(SocialConstants.PARAM_URL)) {
            this.d = jSONObject.getString(SocialConstants.PARAM_URL);
        }
        this.e = jSONObject.getInt("minimumRefreshInterval");
        this.f = jSONObject.getInt("defaultRefreshInterval");
        this.g = jSONObject.getInt("fetchTimeout");
        b(jSONObject.getJSONObject("cache"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("imai");
        this.j.a = jSONObject2.getInt("maxRetries");
        this.j.b = jSONObject2.getInt("pingInterval");
        this.j.c = jSONObject2.getInt("pingTimeout");
        this.j.d = jSONObject2.getInt("maxDbEvents");
        this.j.e = jSONObject2.getInt("maxEventBatch");
        this.j.f = jSONObject2.getLong("pingCacheExpiry");
        jSONObject2 = jSONObject.getJSONObject("rendering");
        this.k.a = jSONObject2.getInt("renderTimeout");
        this.k.c = jSONObject2.getInt("picHeight");
        this.k.b = jSONObject2.getInt("picWidth");
        this.k.d = jSONObject2.getInt("picQuality");
        this.k.e = jSONObject2.getString("webviewBackground");
        this.k.g = jSONObject2.getInt("maxVibrationDuration");
        this.k.h = jSONObject2.getInt("maxVibrationPatternLength");
        this.k.i = (long) jSONObject2.getJSONObject("savecontent").getInt("maxSaveSize");
        synchronized (c) {
            this.k.j.clear();
            JSONArray jSONArray = jSONObject2.getJSONObject("savecontent").getJSONArray("allowedContentType");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.k.j.add(jSONArray.getString(i));
            }
        }
        jSONObject2 = jSONObject.getJSONObject("mraid");
        this.l.a = jSONObject2.getLong("expiry");
        this.l.b = jSONObject2.getInt("maxRetries");
        this.l.c = jSONObject2.getInt("retryInterval");
        this.l.d = jSONObject2.getString(SocialConstants.PARAM_URL);
        if (jSONObject.has("telemetry")) {
            this.n = jSONObject.getJSONObject("telemetry");
        }
        jSONObject2 = jSONObject.getJSONObject("viewability");
        this.m.a = jSONObject2.getInt("impressionMinPercentageViewed");
        this.m.b = jSONObject2.getInt("impressionMinTimeViewed");
        this.m.c = jSONObject2.optInt("visibilityThrottleMillis", R.styleable.AppCompatTheme_buttonStyle);
        this.m.d = jSONObject2.optInt("impressionPollIntervalMillis", 250);
        jSONObject2 = jSONObject.getJSONObject("preload").getJSONObject("base");
        this.o.a = jSONObject2.getBoolean("enabled");
        this.o.b = jSONObject2.getLong("placementExpiry");
        this.o.c = jSONObject2.getInt("maxPreloadedAds");
    }

    private void b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.h = new a();
        this.h.a = jSONObject2.getInt("maxCacheSize");
        this.h.b = jSONObject2.getInt("fetchLimit");
        this.h.c = jSONObject2.getInt("minThreshold");
        this.h.d = jSONObject2.getLong("timeToLive");
        jSONObject.remove("base");
        this.i = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject3 = jSONObject.getJSONObject(str);
            a aVar = new a();
            aVar.a = jSONObject3.has("maxCacheSize") ? jSONObject3.getInt("maxCacheSize") : this.h.a;
            aVar.b = jSONObject3.has("fetchLimit") ? jSONObject3.getInt("fetchLimit") : this.h.b;
            aVar.c = jSONObject3.has("minThreshold") ? jSONObject3.getInt("minThreshold") : this.h.c;
            aVar.d = jSONObject3.has("timeToLive") ? (long) jSONObject3.getInt("timeToLive") : this.h.d;
            this.i.put(str, aVar);
        }
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        b.put(SocialConstants.PARAM_URL, this.d);
        b.put("minimumRefreshInterval", this.e);
        b.put("defaultRefreshInterval", this.f);
        b.put("fetchTimeout", this.g);
        b.put("cache", r());
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("maxRetries", this.j.a());
        jSONObject.put("pingInterval", this.j.b());
        jSONObject.put("pingTimeout", this.j.c());
        jSONObject.put("maxDbEvents", this.j.d());
        jSONObject.put("maxEventBatch", this.j.e());
        jSONObject.put("pingCacheExpiry", this.j.f());
        b.put("imai", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("renderTimeout", this.k.i());
        jSONObject.put("picWidth", this.k.a());
        jSONObject.put("picHeight", this.k.b());
        jSONObject.put("picQuality", this.k.c());
        jSONObject.put("webviewBackground", this.k.e);
        jSONObject.put("maxVibrationDuration", this.k.e());
        jSONObject.put("maxVibrationPatternLength", this.k.f());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxSaveSize", this.k.g());
        jSONObject2.put("allowedContentType", new JSONArray(this.k.h()));
        jSONObject.put("savecontent", jSONObject2);
        b.put("rendering", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("expiry", this.l.a());
        jSONObject.put("maxRetries", this.l.b());
        jSONObject.put("retryInterval", this.l.c());
        jSONObject.put(SocialConstants.PARAM_URL, this.l.d());
        b.put("mraid", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("impressionMinPercentageViewed", this.m.a());
        jSONObject.put("impressionMinTimeViewed", this.m.b());
        jSONObject.put("visibilityThrottleMillis", this.m.c());
        jSONObject.put("impressionPollIntervalMillis", this.m.d());
        b.put("viewability", jSONObject);
        jSONObject = new JSONObject();
        jSONObject2 = new JSONObject();
        jSONObject2.put("enabled", this.o.b());
        jSONObject2.put("placementExpiry", this.o.a());
        jSONObject2.put("maxPreloadedAds", this.o.c());
        jSONObject.put("base", jSONObject2);
        b.put("preload", jSONObject);
        if (this.n != null) {
            b.put("telemetry", this.n);
        }
        return b;
    }

    private JSONObject r() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", this.h.b());
        jSONObject2.put("fetchLimit", this.h.c());
        jSONObject2.put("minThreshold", this.h.d());
        jSONObject2.put("timeToLive", this.h.e());
        jSONObject.put("base", jSONObject2);
        for (Entry entry : this.i.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            a aVar = (a) entry.getValue();
            jSONObject3.put("maxCacheSize", aVar.b());
            jSONObject3.put("fetchLimit", aVar.c());
            jSONObject3.put("minThreshold", aVar.d());
            jSONObject3.put("timeToLive", aVar.e());
            jSONObject.put((String) entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    public final boolean c() {
        if ((!this.d.startsWith("http://") && !this.d.startsWith("https://")) || this.e < 0 || this.f < 0 || this.g <= 0) {
            return false;
        }
        if (this.h == null || !this.h.a()) {
            return false;
        }
        for (Entry entry : this.i.entrySet()) {
            if (!((a) entry.getValue()).a()) {
                return false;
            }
        }
        if (this.j.d() < 0 || this.j.e() < 0 || this.j.a() < 0 || this.j.b() < 0 || this.j.c() <= 0 || this.j.f() <= 0) {
            return false;
        }
        if (this.l.a() < 0 || this.l.c() < 0 || this.l.b() < 0 || (!this.l.d().startsWith("http://") && !this.l.d().startsWith("https://"))) {
            return false;
        }
        if (this.k.i() < 0 || this.k.b() < 0 || this.k.a() < 0 || this.k.c() < 0 || this.k.e() < 0 || this.k.f() < 0 || this.k.g() < 0 || this.k.e == null || this.k.e.trim().length() == 0) {
            return false;
        }
        try {
            this.k.f = Color.parseColor(this.k.e);
            if (this.l.b() < 0 || this.l.c() < 0 || this.l.d() == null || this.l.d().trim().length() == 0) {
                return false;
            }
            if (this.m.a() <= 0 || this.m.a() > 100 || this.m.b() < 0 || this.m.c() < 50 || this.m.c() * 5 > this.m.b() || this.m.d() < 50 || this.m.d() * 4 > this.m.b()) {
                return false;
            }
            return this.o.b >= 0 && this.o.c > 0;
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Webview color specified in config is invalid.", e);
            return false;
        }
    }

    public final com.inmobi.commons.core.configs.a d() {
        return new b();
    }

    public final String e() {
        return this.d;
    }

    public final int f() {
        return this.e;
    }

    public final int g() {
        return this.f;
    }

    public final int h() {
        return this.g;
    }

    public final a a(String str) {
        a aVar = (a) this.i.get(str);
        return aVar == null ? this.h : aVar;
    }

    public final b i() {
        return this.j;
    }

    public final e j() {
        return this.k;
    }

    public final c k() {
        return this.l;
    }

    public final f l() {
        return this.m;
    }

    public final JSONObject m() {
        return this.n;
    }

    public final d n() {
        return this.o;
    }
}
