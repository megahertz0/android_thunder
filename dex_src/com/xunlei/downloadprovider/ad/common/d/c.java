package com.xunlei.downloadprovider.ad.common.d;

import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.t;
import java.util.Map;

// compiled from: SSPRequest.java
public class c extends t {
    private static final String a;
    private a b;

    static {
        a = c.class.getSimpleName();
    }

    private c(String str, b<String> bVar, a aVar) {
        this(str, bVar, aVar, (byte) 0);
    }

    private c(String str, b<String> bVar, a aVar, byte b) {
        super(1, str, bVar, aVar);
    }

    public c(a aVar, b<String> bVar, a aVar2) {
        this(aVar.a(), (b) bVar, aVar2);
        this.b = aVar;
    }

    protected Map getParams() {
        return this.b != null ? this.b.b() : null;
    }
}
