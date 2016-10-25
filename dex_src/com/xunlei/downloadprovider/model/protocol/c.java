package com.xunlei.downloadprovider.model.protocol;

import android.os.Handler;
import anet.channel.util.HttpConstant;
import com.xunlei.downloadprovider.b.b.d;
import com.xunlei.downloadprovider.model.protocol.g.a;
import com.xunlei.downloadprovider.model.protocol.g.b;
import com.xunlei.downloadprovider.model.protocol.g.e;
import com.xunlei.downloadprovider.model.protocol.g.f;
import com.xunlei.downloadprovider.model.protocol.g.g;
import com.xunlei.downloadprovider.model.protocol.g.i;
import com.xunlei.downloadprovider.model.protocol.g.j;

// compiled from: DownloadProviderProtocol.java
public final class c {
    private static c a;

    static {
        a = null;
    }

    private c() {
    }

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public static int a(String str, Handler handler, Object obj) {
        a aVar = new a(handler, obj);
        d dVar = new d(str);
        dVar.d = new b(aVar);
        dVar.c = new com.xunlei.downloadprovider.model.protocol.g.c(aVar);
        dVar.a = new com.xunlei.downloadprovider.model.protocol.g.d(aVar);
        aVar.setBpFuture(dVar);
        return com.xunlei.downloadprovider.b.a.runBox(aVar);
    }

    public static int a(Handler handler, int i) {
        return new com.xunlei.downloadprovider.model.protocol.f.a(handler, i).a();
    }

    public static int b(String str, Handler handler, Object obj) {
        com.xunlei.downloadprovider.b.a eVar = new e(handler, obj);
        com.xunlei.downloadprovider.b.b.a aVar = new com.xunlei.downloadprovider.b.b.a(str, new f(eVar));
        aVar.a = new g(eVar);
        aVar.a(HttpConstant.ACCEPT, "application/json");
        aVar.a();
        eVar.setBpFuture(aVar);
        return com.xunlei.downloadprovider.b.a.runBox(eVar);
    }

    public static int a(String str, Handler handler) {
        com.xunlei.downloadprovider.b.a eVar = new e(handler, null);
        com.xunlei.downloadprovider.b.b.a aVar = new com.xunlei.downloadprovider.b.b.a(new StringBuilder("http://u.155.com/download/info?package=").append(str).toString(), new i(eVar));
        aVar.a = new j(eVar);
        aVar.a(HttpConstant.ACCEPT, "application/json");
        aVar.a();
        eVar.setBpFuture(aVar);
        return com.xunlei.downloadprovider.b.a.runBox(eVar);
    }
}
