package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.umeng.a;
import org.json.JSONObject;

// compiled from: ResizeProperties.java
public class m {
    private static final String g;
    String a;
    int b;
    int c;
    int d;
    int e;
    boolean f;

    static {
        g = m.class.getSimpleName();
    }

    public m() {
        this.d = 0;
        this.e = 0;
        this.a = "top-right";
        this.f = true;
    }

    public static m a(String str, m mVar) {
        m mVar2 = new m();
        try {
            JSONObject jSONObject = new JSONObject(str);
            mVar2.b = jSONObject.getInt("width");
            mVar2.c = jSONObject.getInt("height");
            mVar2.d = jSONObject.getInt("offsetX");
            mVar2.e = jSONObject.getInt("offsetY");
            if (mVar == null) {
                return mVar2;
            }
            mVar2.a = jSONObject.optString("customClosePosition", mVar.a);
            mVar2.f = jSONObject.optBoolean("allowOffscreen", mVar.f);
            return mVar2;
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, g, "Invalid resize properties string passed.", e);
            return null;
        }
    }

    public String a() {
        String str = a.d;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("width", this.b);
            jSONObject.put("height", this.c);
            jSONObject.put("customClosePosition", this.a);
            jSONObject.put("offsetX", this.d);
            jSONObject.put("offsetY", this.e);
            jSONObject.put("allowOffscreen", this.f);
            return jSONObject.toString();
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, g, "Invalid resize properties string passed.", e);
            return str;
        }
    }
}
