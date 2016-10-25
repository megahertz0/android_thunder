package com.xunlei.downloadprovider.frame.advertisement.b;

import android.content.Context;
import com.xunlei.downloadprovider.a.j;

// compiled from: AdvertisementUtil.java
public class c$a {
    public static long a;
    private static c$a c;
    Context b;

    static {
        a = 0;
    }

    private c$a(Context context) {
        this.b = context;
    }

    public static c$a a(Context context) {
        if (c == null) {
            c = new c$a(context.getApplicationContext());
        }
        return c;
    }

    public final void a(long j, long j2, String str) {
        j jVar = new j(this.b, "preference_revive_ad");
        jVar.a("backgroud_moment", j);
        jVar.a("backgroud_session", j2);
        jVar.a("last_context_name", str);
    }
}
