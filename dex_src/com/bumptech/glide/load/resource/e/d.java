package com.bumptech.glide.load.resource.e;

import com.bumptech.glide.h.g;
import java.util.HashMap;
import java.util.Map;

// compiled from: TranscoderRegistry.java
public final class d {
    private static final g a;
    private final Map<g, c<?, ?>> b;

    public d() {
        this.b = new HashMap();
    }

    static {
        a = new g();
    }

    public final <Z, R> void a(Class<Z> cls, Class<R> cls2, c<Z, R> cVar) {
        this.b.put(new g(cls, cls2), cVar);
    }

    public final <Z, R> c<Z, R> a(Class<Z> cls, Class<R> cls2) {
        if (cls.equals(cls2)) {
            return e.b();
        }
        synchronized (a) {
            a.a(cls, cls2);
            c<Z, R> cVar = (c) this.b.get(a);
        }
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalArgumentException(new StringBuilder("No transcoder registered for ").append(cls).append(" and ").append(cls2).toString());
    }
}
