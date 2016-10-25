package com.inmobi.signals.activityrecognition;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.umeng.message.MsgConstant;
import org.json.JSONObject;

// compiled from: ActivityInfo.java
public class a {
    private static final String a;
    private int b;
    private long c;

    static {
        a = a.class.getSimpleName();
    }

    public a(int i, long j) {
        this.b = i;
        this.c = j;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", this.b);
            jSONObject.put(MsgConstant.KEY_TS, this.c);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while converting WifiInfo to string.", e);
        }
        return jSONObject;
    }
}
