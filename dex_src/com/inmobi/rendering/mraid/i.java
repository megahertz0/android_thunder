package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.b.c;

// compiled from: MraidJsDao.java
public class i {
    private c a;

    public static String a() {
        return c.a("mraid_js_store");
    }

    public i() {
        this.a = c.b("mraid_js_store");
    }

    public void a(String str) {
        this.a.a("mraid_js_string", str);
        this.a.a("last_updated_ts", System.currentTimeMillis() / 1000);
    }

    public String b() {
        return this.a.b("mraid_js_string", null);
    }

    public long c() {
        return this.a.b("last_updated_ts", 0);
    }
}
