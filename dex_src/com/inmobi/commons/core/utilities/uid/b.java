package com.inmobi.commons.core.utilities.uid;

import android.content.Context;
import com.inmobi.commons.core.b.c;

// compiled from: UidDao.java
class b {
    private c a;

    public b() {
        this.a = c.b("uid_store");
    }

    public b(Context context) {
        this.a = c.a(context, "uid_store");
    }

    public void a(String str) {
        this.a.a("adv_id", str);
    }

    public String a() {
        return this.a.b("adv_id", null);
    }

    public void a(boolean z) {
        this.a.a("limit_ad_tracking", z);
    }

    public boolean b() {
        return this.a.b("limit_ad_tracking", true);
    }

    public void b(String str) {
        this.a.a("app_id", str);
    }

    public String c() {
        return this.a.b("app_id", null);
    }

    public void c(String str) {
        this.a.a("im_id", str);
    }

    public String d() {
        return this.a.b("im_id", null);
    }

    public void a(long j) {
        this.a.a("imid_timestamp", j);
    }

    public long e() {
        return this.a.b("imid_timestamp", 0);
    }

    public void d(String str) {
        this.a.a("appended_id", str);
    }

    public String f() {
        return this.a.b("appended_id", null);
    }
}
