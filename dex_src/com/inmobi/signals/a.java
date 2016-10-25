package com.inmobi.signals;

import com.inmobi.commons.core.b.c;

// compiled from: CarbDao.java
public class a {
    private c a;

    public static String a() {
        return c.a("carb_store");
    }

    public a() {
        this.a = c.b("carb_store");
    }

    public long b() {
        return this.a.b("carb_last_update_ts", 0);
    }

    public void a(long j) {
        this.a.a("carb_last_update_ts", j);
    }
}
