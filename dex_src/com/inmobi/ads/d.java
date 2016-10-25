package com.inmobi.ads;

import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

// compiled from: AdNetworkClient.java
final class d implements com.inmobi.commons.core.network.a.a {
    private static final String a;
    private e b;
    private a c;

    // compiled from: AdNetworkClient.java
    public static interface a {
        void a(f fVar);

        void b(f fVar);
    }

    static {
        a = d.class.getSimpleName();
    }

    public d(e eVar, a aVar) {
        this.b = eVar;
        this.c = aVar;
    }

    public final void a() {
        new com.inmobi.commons.core.network.a(this.b, this).a();
    }

    public final void a(c cVar) {
        f fVar = new f(this.b, cVar);
        this.c.a(fVar);
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Ad fetch succeeded. Response:").append(fVar.c()).toString());
    }

    public final void b(c cVar) {
        f fVar = new f(this.b, cVar);
        this.c.b(fVar);
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Ad fetch failed:").append(fVar.d().b()).toString());
    }
}
