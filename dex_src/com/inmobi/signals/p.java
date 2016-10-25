package com.inmobi.signals;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: SignalsConfig.java
public class p extends com.inmobi.commons.core.configs.a {
    private static final String a;
    private b b;
    private a c;
    private JSONObject d;

    // compiled from: SignalsConfig.java
    public static class a {
        private boolean a;
        private String b;
        private String c;
        private int d;
        private int e;
        private int f;
        private int g;
        private long h;

        public a() {
            this.a = false;
            this.b = "http://dock.inmobi.com/carb/v1/i";
            this.c = "http://dock.inmobi.com/carb/v1/o";
            this.d = 86400;
            this.e = 3;
            this.f = 60;
            this.g = 60;
            this.h = 307200;
        }

        public boolean a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public int d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }

        public int f() {
            return this.f;
        }

        public int g() {
            return this.g;
        }

        public long h() {
            return this.h;
        }
    }

    // compiled from: SignalsConfig.java
    public static class b {
        private boolean a;
        private int b;
        private int c;
        private int d;
        private String e;
        private int f;
        private int g;
        private boolean h;
        private boolean i;
        private int j;
        private boolean k;
        private boolean l;
        private int m;
        private boolean n;
        private boolean o;
        private boolean p;
        private boolean q;
        private int r;
        private int s;

        public b() {
            this.a = false;
            this.b = 300;
            this.c = 3;
            this.d = 50;
            this.e = "https://sdkm.w.inmobi.com/user/e.asm";
            this.f = 3;
            this.g = 60;
            this.h = false;
            this.i = false;
            this.j = 0;
            this.k = false;
            this.l = false;
            this.m = 0;
            this.n = false;
            this.o = false;
            this.p = false;
            this.q = false;
            this.r = 180;
            this.s = 50;
        }

        public boolean a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public int d() {
            return this.d;
        }

        public String e() {
            return this.e;
        }

        public int f() {
            return this.f;
        }

        public int g() {
            return this.g;
        }

        public boolean h() {
            return this.h && this.a;
        }

        public boolean i() {
            return this.i && this.a;
        }

        public int j() {
            return this.j;
        }

        public boolean k() {
            return this.k && this.a;
        }

        public boolean l() {
            return this.l && this.a;
        }

        public int m() {
            return this.m;
        }

        public boolean n() {
            return this.n && this.a;
        }

        public boolean o() {
            return this.o && this.a;
        }

        public boolean p() {
            return this.p && this.a;
        }

        public boolean q() {
            return this.q && this.a;
        }

        public int r() {
            return this.r;
        }

        public int s() {
            return this.s;
        }
    }

    static {
        a = com.inmobi.commons.core.configs.a.class.getSimpleName();
    }

    private JSONObject h() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", false);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", false);
        return jSONObject;
    }

    public p() {
        this.b = new b();
        this.c = new a();
        try {
            this.d = h();
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Default telemetry config provided for ads is invalid.", e);
        }
    }

    public String a() {
        return "signals";
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("ice");
        this.b.b = jSONObject2.getInt("sampleInterval");
        this.b.d = jSONObject2.getInt("sampleHistorySize");
        this.b.c = jSONObject2.getInt("stopRequestTimeout");
        this.b.a = jSONObject2.getBoolean("enabled");
        this.b.e = jSONObject2.getString("endPoint");
        this.b.f = jSONObject2.getInt("maxRetries");
        this.b.g = jSONObject2.getInt("retryInterval");
        this.b.h = jSONObject2.getBoolean("locationEnabled");
        this.b.i = jSONObject2.getBoolean("sessionEnabled");
        JSONObject jSONObject3 = jSONObject2.getJSONObject(IXAdRequestInfo.WIDTH);
        this.b.j = jSONObject3.getInt("wf");
        this.b.l = jSONObject3.getBoolean("cwe");
        this.b.k = jSONObject3.getBoolean("vwe");
        jSONObject3 = jSONObject2.getJSONObject("c");
        this.b.n = jSONObject3.getBoolean("oe");
        this.b.p = jSONObject3.getBoolean("cce");
        this.b.o = jSONObject3.getBoolean("vce");
        this.b.m = jSONObject3.getInt("cof");
        jSONObject2 = jSONObject2.getJSONObject("ar");
        this.b.q = jSONObject2.getBoolean("e");
        this.b.r = jSONObject2.getInt("sampleInterval");
        this.b.s = jSONObject2.getInt("maxHistorySize");
        jSONObject2 = jSONObject.getJSONObject("carb");
        this.c.a = jSONObject2.getBoolean("enabled");
        this.c.b = jSONObject2.getString("getEndPoint");
        this.c.c = jSONObject2.getString("postEndPoint");
        this.c.d = jSONObject2.getInt("retrieveFrequency");
        this.c.e = jSONObject2.getInt("maxRetries");
        this.c.f = jSONObject2.getInt("retryInterval");
        this.c.g = jSONObject2.getInt("timeoutInterval");
        this.c.h = jSONObject2.getLong("maxGetResponseSize");
        this.d = jSONObject.optJSONObject("telemetry");
    }

    public JSONObject b() throws JSONException {
        JSONObject b = super.b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sampleInterval", this.b.b);
        jSONObject.put("stopRequestTimeout", this.b.c);
        jSONObject.put("sampleHistorySize", this.b.d);
        jSONObject.put("enabled", this.b.a);
        jSONObject.put("endPoint", this.b.e);
        jSONObject.put("maxRetries", this.b.f);
        jSONObject.put("retryInterval", this.b.g);
        jSONObject.put("locationEnabled", this.b.h);
        jSONObject.put("sessionEnabled", this.b.i);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("wf", this.b.j);
        jSONObject2.put("vwe", this.b.k);
        jSONObject2.put("cwe", this.b.l);
        jSONObject.put(IXAdRequestInfo.WIDTH, jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("cof", this.b.m);
        jSONObject2.put("vce", this.b.o);
        jSONObject2.put("cce", this.b.p);
        jSONObject2.put("oe", this.b.n);
        jSONObject.put("c", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.put("e", this.b.q);
        jSONObject2.put("sampleInterval", this.b.r);
        jSONObject2.put("maxHistorySize", this.b.s);
        jSONObject.put("ar", jSONObject2);
        b.put("ice", jSONObject);
        jSONObject = new JSONObject();
        jSONObject.put("enabled", this.c.a);
        jSONObject.put("getEndPoint", this.c.b);
        jSONObject.put("postEndPoint", this.c.c);
        jSONObject.put("retrieveFrequency", this.c.d);
        jSONObject.put("maxRetries", this.c.e);
        jSONObject.put("retryInterval", this.c.f);
        jSONObject.put("timeoutInterval", this.c.g);
        jSONObject.put("maxGetResponseSize", this.c.h());
        b.put("carb", jSONObject);
        b.put("telemetry", this.d);
        return b;
    }

    public boolean c() {
        if (this.b.b < 0 || this.b.d < 0 || this.b.c < 0 || this.b.e.trim().length() == 0 || this.b.f < 0 || this.b.g < 0 || this.b.j() < 0 || this.b.m() < 0 || this.b.s < 0 || this.b.r < 0 || this.c.b.trim().length() == 0 || this.c.c.trim().length() == 0) {
            return false;
        }
        if (this.c.b.startsWith("http://") || this.c.b.startsWith("https://")) {
            return (this.c.c.startsWith("http://") || this.c.c.startsWith("https://")) && this.c.d >= 0 && this.c.e >= 0 && this.c.f >= 0 && this.c.g >= 0 && this.c.h >= 0;
        } else {
            return false;
        }
    }

    public com.inmobi.commons.core.configs.a d() {
        return new p();
    }

    public JSONObject e() {
        return this.d;
    }

    public b f() {
        return this.b;
    }

    public a g() {
        return this.c;
    }
}
