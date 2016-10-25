package com.inmobi.signals;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.f;
import java.util.UUID;

// compiled from: SessionManager.java
public class n {
    private static final String a;
    private static n b;
    private static Object c;

    static {
        a = n.class.getSimpleName();
        c = new Object();
    }

    public static n a() {
        n nVar = b;
        if (nVar == null) {
            synchronized (c) {
                nVar = b;
                if (nVar == null) {
                    nVar = new n();
                    b = nVar;
                }
            }
        }
        return nVar;
    }

    private n() {
    }

    void b() {
        if (o.a().e().i()) {
            f.a().a(UUID.randomUUID().toString());
            f.a().a(System.currentTimeMillis());
            f.a().b(0);
            Logger.a(InternalLogLevel.INTERNAL, a, "Session tracking started.");
        }
    }

    void c() {
        if (o.a().e().i()) {
            f.a().b(System.currentTimeMillis());
            Logger.a(InternalLogLevel.INTERNAL, a, "Session tracking stopped.");
        }
    }

    f d() {
        return !o.a().e().i() ? null : f.a();
    }
}
