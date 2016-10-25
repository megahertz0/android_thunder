package com.inmobi.commons.core.utilities.a;

import com.inmobi.commons.core.b.c;

// compiled from: EncryptionDao.java
public class a {
    private c a;

    public static String a() {
        return c.a("aes_key_store");
    }

    public a() {
        this.a = c.b("aes_key_store");
    }

    public void a(String str) {
        this.a.a("aes_public_key", str);
        this.a.a("last_generated_ts", System.currentTimeMillis() / 1000);
    }

    public String b() {
        return this.a.b("aes_public_key", null);
    }

    public long c() {
        return this.a.b("last_generated_ts", 0);
    }
}
