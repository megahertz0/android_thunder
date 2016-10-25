package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.d;

// compiled from: IceNetworkRequest.java
public class k extends NetworkRequest {
    private static final String d;
    private int e;
    private int f;
    private l g;

    static {
        d = k.class.getSimpleName();
    }

    public k(String str, int i, int i2, d dVar, l lVar) {
        super(RequestType.POST, str, true, dVar);
        this.e = i;
        this.f = i2;
        this.g = lVar;
        String toString = this.g.a().toString();
        this.c.put("payload", toString);
        Logger.a(InternalLogLevel.INTERNAL, d, new StringBuilder("Ice payload being sent:").append(toString).toString());
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }
}
