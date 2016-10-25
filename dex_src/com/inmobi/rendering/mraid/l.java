package com.inmobi.rendering.mraid;

import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

// compiled from: OrientationProperties.java
public class l {
    private static String e;
    public boolean a;
    public String b;
    public String c;
    private String d;

    static {
        e = l.class.getSimpleName();
    }

    public l() {
        this.b = IXAdSystemUtils.NT_NONE;
        this.c = "right";
        this.a = true;
        this.d = null;
    }

    public static l a(String str, l lVar) {
        l lVar2 = new l();
        lVar2.d = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            lVar2.b = jSONObject.optString("forceOrientation", lVar.b);
            lVar2.a = jSONObject.optBoolean("allowOrientationChange", lVar.a);
            lVar2.c = jSONObject.optString("direction", lVar.c);
            if (!(lVar2.b.equals("portrait") || lVar2.b.equals("landscape"))) {
                lVar2.b = IXAdSystemUtils.NT_NONE;
            }
            if (lVar2.c.equals("left") || lVar2.c.equals("right")) {
                return lVar2;
            }
            lVar2.c = "right";
            return lVar2;
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, e, "Invalid orientation properties string passed.", e);
            return null;
        }
    }

    public String a() {
        return this.d;
    }
}
