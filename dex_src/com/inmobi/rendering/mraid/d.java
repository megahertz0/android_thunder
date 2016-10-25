package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ExpandProperties.java
public class d {
    private static final String c;
    public int a;
    public int b;
    private boolean d;
    private boolean e;
    private boolean f;
    private String g;

    static {
        c = d.class.getSimpleName();
    }

    public d() {
        this.a = DisplayInfo.a().b();
        this.b = DisplayInfo.a().a();
        this.d = false;
        this.f = true;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.a);
            jSONObject.put("height", this.b);
            jSONObject.put("useCustomClose", this.d);
            jSONObject.put("isModal", this.f);
        } catch (JSONException e) {
            Logger.a(InternalLogLevel.INTERNAL, c, new StringBuilder("Exception in composing ExpandProperties: ").append(e.getMessage()).toString());
        }
        this.g = jSONObject.toString();
    }

    public boolean a() {
        return this.d;
    }

    public boolean b() {
        return this.e;
    }

    public static d a(String str, d dVar, l lVar) {
        d dVar2 = new d();
        dVar2.g = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            dVar2.f = true;
            if (jSONObject.has("useCustomClose")) {
                dVar2.e = true;
            }
            dVar2.d = jSONObject.optBoolean("useCustomClose", false);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, c, "Invalid expand properties string passed.", e);
        }
        return dVar2;
    }

    public String c() {
        return this.g;
    }
}
