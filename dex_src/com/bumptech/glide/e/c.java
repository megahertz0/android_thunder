package com.bumptech.glide.e;

import com.bumptech.glide.h.g;
import java.util.HashMap;
import java.util.Map;

// compiled from: DataLoadProviderRegistry.java
public final class c {
    private static final g a;
    private final Map<g, b<?, ?>> b;

    public c() {
        this.b = new HashMap();
    }

    static {
        a = new g();
    }

    public final <T, Z> void a(Class<T> cls, Class<Z> cls2, b<T, Z> bVar) {
        this.b.put(new g(cls, cls2), bVar);
    }

    public final <T, Z> b<T, Z> a(Class<T> cls, Class<Z> cls2) {
        b<T, Z> bVar;
        synchronized (a) {
            a.a(cls, cls2);
            bVar = (b) this.b.get(a);
        }
        return bVar == null ? d.e() : bVar;
    }
}
