package com.inmobi.signals;

import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

// compiled from: CarbNetworkClient.java
public class e {
    private static final String a;

    static {
        a = e.class.getSimpleName();
    }

    public c a(b bVar) {
        c cVar = new c(new d(bVar).a());
        if (cVar.a()) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Getting Carb list from server failed.");
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, "Getting Carb list from server succeeded.");
        }
        return cVar;
    }

    public boolean a(f fVar) {
        boolean z = false;
        int i = 0;
        while (i <= fVar.b()) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Attempting to send pruned Carb list to server.");
            if (!new d(fVar).a().a()) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Sending pruned Carb list to server succeeded.");
                z = true;
                break;
            }
            Logger.a(InternalLogLevel.INTERNAL, a, "Sending pruned Carb list to server failed.");
            i++;
            if (i > fVar.b()) {
                break;
            }
            try {
                Thread.sleep((long) (fVar.c() * 1000));
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "User data network client interrupted while sleeping.", e);
            }
        }
        if (!z) {
            a.a().a(new com.inmobi.commons.core.c.e("signals", "CarbUploadDiscarded"));
        }
        return z;
    }
}
