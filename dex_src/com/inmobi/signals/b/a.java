package com.inmobi.signals.b;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

// compiled from: WifiInfo.java
public class a {
    private static final String a;
    private long b;
    private String c;
    private int d;
    private int e;

    static {
        a = a.class.getSimpleName();
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public long a() {
        return this.b;
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(anet.channel.strategy.dispatch.a.BSSID, this.b);
            jSONObject.put("essid", this.c);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while converting WifiInfo to string.", e);
        }
        return jSONObject;
    }
}
