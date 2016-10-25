package com.xunlei.downloadprovider.member.a.a;

import com.android.volley.toolbox.t;
import com.xunlei.downloadprovider.member.a.a;

// compiled from: HighSpeedConfig.java
public final class b implements a {
    a.a a;
    private a b;

    public b(a.a aVar) {
        this.a = aVar;
        this.b = new a(this);
    }

    public final void a() {
        if (!c.b()) {
            b();
        } else if (this.b != null) {
            a aVar = this.b;
            if (aVar.a != null) {
                t tVar = new t("http://try.service.cdn.vip.xunlei.com:80/high_speed_channel/scene/config/get", aVar, aVar);
                tVar.setShouldCache(false);
                com.xunlei.downloadprovider.j.a.a().e().a(tVar);
            }
        }
    }

    protected final void b() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public static String a(String str) {
        return c.a(str);
    }
}
