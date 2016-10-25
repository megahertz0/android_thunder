package com.inmobi.commons.core.configs;

import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ConfigDao.java
public class c {
    private com.inmobi.commons.core.b.c a;

    public static String a() {
        return com.inmobi.commons.core.b.c.a("config_store");
    }

    public c() {
        this.a = com.inmobi.commons.core.b.c.b("config_store");
    }

    public void a(a aVar) {
        try {
            this.a.a(aVar.a() + "_config", aVar.b().toString());
            a(aVar.a(), System.currentTimeMillis());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void b(a aVar) {
        String b = this.a.b(aVar.a() + "_config", null);
        if (b != null) {
            try {
                aVar.a(new JSONObject(b));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean a(String str) {
        return this.a.b(new StringBuilder().append(str).append("_config").toString(), null) != null;
    }

    public long b(String str) {
        return this.a.b(str + "_config_update_ts", 0);
    }

    public void a(String str, long j) {
        this.a.a(str + "_config_update_ts", j);
    }
}
