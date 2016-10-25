package com.inmobi.commons.core.a;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CrashConfig.java
public class a extends com.inmobi.commons.core.configs.a {
    private static final String a;
    private JSONObject b;

    static {
        a = a.class.getSimpleName();
    }

    private JSONObject f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", false);
        jSONObject.put("samplingFactor", 0);
        jSONObject.put("metricEnabled", false);
        return jSONObject;
    }

    public a() {
        try {
            this.b = f();
        } catch (Exception e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error in default telemetry config");
        }
    }

    public void a(JSONObject jSONObject) {
        try {
            super.a(jSONObject);
            this.b = jSONObject.getJSONObject("telemetry");
        } catch (JSONException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Error parsing Crash Config ").append(e.toString()).toString());
        }
    }

    public JSONObject b() {
        try {
            JSONObject b = super.b();
            b.put("telemetry", this.b);
            return b;
        } catch (JSONException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Error parsing Crash Config ").append(e.toString()).toString());
            return null;
        }
    }

    public JSONObject e() {
        return this.b;
    }

    public String a() {
        return "crashReporting";
    }

    public boolean c() {
        return true;
    }

    public com.inmobi.commons.core.configs.a d() {
        return new a();
    }
}
