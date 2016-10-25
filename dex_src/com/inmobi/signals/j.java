package com.inmobi.signals;

import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.c.e;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

// compiled from: IceNetworkClient.java
public class j {
    private static final String a;
    private k b;

    static {
        a = j.class.getSimpleName();
    }

    public j(k kVar) {
        this.b = kVar;
    }

    public void a() {
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (i <= j.this.b.b()) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Attempting to send samples to server.");
                    if (new d(j.this.b).a().a()) {
                        Logger.a(InternalLogLevel.INTERNAL, a, "Sending samples to server failed.");
                        i++;
                        if (i > j.this.b.b()) {
                            a.a().a(new e("signals", "RetryCountExceeded"));
                            return;
                        }
                        try {
                            Thread.sleep((long) (j.this.b.c() * 1000));
                        } catch (Throwable e) {
                            Logger.a(InternalLogLevel.INTERNAL, a, "User data network client interrupted while sleeping.", e);
                        }
                    } else {
                        Logger.a(InternalLogLevel.INTERNAL, a, "Sending samples to server succeeded.");
                        return;
                    }
                }
            }
        }).start();
    }
}
