package com.inmobi.rendering.mraid;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

// compiled from: MraidJsFetcher.java
public class j {
    private static final String a;
    private String b;
    private int c;
    private int d;
    private NetworkRequest e;

    static {
        a = j.class.getSimpleName();
    }

    public j(String str, int i, int i2) {
        this.b = str;
        this.c = i;
        this.d = i2;
    }

    public void a() {
        if (this.b == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, "MRAID Js Url provided is invalid.");
            return;
        }
        this.e = new NetworkRequest(RequestType.GET, this.b, false, null);
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (i <= j.this.c) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Attempting to get MRAID Js.");
                    c a = new d(j.this.e).a();
                    if (a.a()) {
                        Logger.a(InternalLogLevel.INTERNAL, a, "Getting MRAID Js from server failed.");
                        i++;
                        if (i <= j.this.c) {
                            try {
                                Thread.sleep((long) (j.this.d * 1000));
                            } catch (Throwable e) {
                                Logger.a(InternalLogLevel.INTERNAL, a, "MRAID Js client interrupted while sleeping.", e);
                            }
                        } else {
                            return;
                        }
                    }
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Getting MRAID Js from server succeeded. Response:").append(a.b()).toString());
                    new i().a(a.b());
                    return;
                }
            }
        }).start();
    }
}
