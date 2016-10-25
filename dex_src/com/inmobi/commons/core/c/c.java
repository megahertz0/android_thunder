package com.inmobi.commons.core.c;

import com.inmobi.commons.core.configs.a;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: TelemetryConfig.java
class c extends a {
    private b a;
    private String b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    public c() {
        this.b = "https://sdkm.w.inmobi.com/metrics/e.asm?v=1&";
        this.c = 300;
        this.d = 60;
        this.e = 50;
        this.f = 3;
        this.g = 1000;
        this.h = 10;
        this.a = new b();
    }

    public String a() {
        return "telemetry";
    }

    public boolean c() {
        if (this.a == null || this.a.c() < 0 || this.b.trim().length() == 0) {
            return false;
        }
        return (this.b.startsWith("http://") || this.b.startsWith("https://")) && this.d >= 0 && this.c >= 0 && this.f >= 0 && this.h > 0 && this.e > 0 && this.g > 0;
    }

    public void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.a.b(jSONObject2.getBoolean("enabled"));
        this.a.a(jSONObject2.getInt("samplingFactor"));
        this.a.a(jSONObject2.getBoolean("metricEnabled"));
        a(jSONObject.getString(SocialConstants.PARAM_URL));
        a(jSONObject.getInt("processingInterval"));
        f(jSONObject.getInt("retryInterval"));
        c(jSONObject.getInt("maxBatchSize"));
        b(jSONObject.getInt("maxRetryCount"));
        d(jSONObject.getInt("maxEventsToPersist"));
        e(jSONObject.getInt("memoryThreshold"));
    }

    public JSONObject b() throws JSONException {
        JSONObject b = super.b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", this.a.b());
        jSONObject.put("samplingFactor", this.a.c());
        jSONObject.put("metricEnabled", this.a.d());
        b.put("base", jSONObject);
        b.put(SocialConstants.PARAM_URL, f());
        b.put("processingInterval", k());
        b.put("retryInterval", g());
        b.put("maxBatchSize", i());
        b.put("maxRetryCount", j());
        b.put("maxEventsToPersist", l());
        b.put("memoryThreshold", h());
        return b;
    }

    public boolean e() {
        return this.a.b();
    }

    private void a(String str) {
        this.b = str;
    }

    public String f() {
        return this.b;
    }

    private void a(int i) {
        this.c = i;
    }

    private void b(int i) {
        this.f = i;
    }

    private void c(int i) {
        this.e = i;
    }

    private void d(int i) {
        this.g = i;
    }

    private void e(int i) {
        this.h = i;
    }

    private void f(int i) {
        this.d = i;
    }

    public int g() {
        return this.d;
    }

    public int h() {
        return this.h;
    }

    public int i() {
        return this.e;
    }

    public int j() {
        return this.f;
    }

    public int k() {
        return this.c;
    }

    public int l() {
        return this.g;
    }

    public a d() {
        return new c();
    }

    public b m() {
        return this.a;
    }
}
