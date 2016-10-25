package com.xunlei.downloadprovider.download.c;

import com.android.volley.Request;
import com.android.volley.p;
import com.android.volley.toolbox.n;
import com.xunlei.downloadprovider.j.a;
import java.util.List;

// compiled from: SpeedLimitManager.java
public final class c {
    private static c c;
    List<g> a;
    public boolean b;

    public static c a() {
        if (c == null) {
            c = new c();
        }
        return c;
    }

    public final void b() {
        c a = a();
        f fVar = new f(this);
        p d = a.a().d();
        Request nVar = new n("http://static.m.sjzhushou.com/pss/static/download/download_speed.json", new d(a, fVar), new e(a));
        nVar.setShouldCache(false);
        d.a(nVar);
    }
}
