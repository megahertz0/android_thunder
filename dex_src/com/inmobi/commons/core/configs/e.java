package com.inmobi.commons.core.configs;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.d;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: ConfigNetworkRequest.java
final class e extends NetworkRequest {
    private static final String d;
    private static String h;
    private int e;
    private int f;
    private Map<String, a> g;

    static {
        d = e.class.getSimpleName();
        h = "http://config.inmobi.com/config-server/v1/config/secure.cfg";
    }

    public e(Map<String, a> map, d dVar, String str, int i, int i2) {
        RequestType requestType = RequestType.POST;
        if (str == null || str.trim().length() == 0) {
            str = h;
        }
        super(requestType, str, true, dVar);
        this.g = map;
        this.e = i;
        this.f = i2;
    }

    public final void a() {
        super.a();
        this.c.put("p", e());
        this.c.put("im-accid", a.c());
    }

    private String e() {
        c cVar = new c();
        String str = com.umeng.a.d;
        try {
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.g.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(IXAdRequestInfo.AD_COUNT, entry.getKey());
                jSONObject.put(anet.channel.strategy.dispatch.a.TIMESTAMP, cVar.b((String) entry.getKey()));
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, d, "Problem while creating config network request's payload.", e);
            return str;
        }
    }

    public final Map<String, a> b() {
        return this.g;
    }

    public final int c() {
        return this.e;
    }

    public final int d() {
        return this.f;
    }

    public final void a(String str) {
        this.g.remove(str);
    }
}
