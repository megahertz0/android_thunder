package com.bumptech.glide.load.engine.a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: GroupedLinkedMap.java
final class e<K extends h, V> {
    private final a<K, V> a;
    private final Map<K, a<K, V>> b;

    // compiled from: GroupedLinkedMap.java
    private static class a<K, V> {
        final K a;
        List<V> b;
        a<K, V> c;
        a<K, V> d;

        public a() {
            this(null);
        }

        public a(K k) {
            this.d = this;
            this.c = this;
            this.a = k;
        }

        public final V a() {
            int b = b();
            return b > 0 ? this.b.remove(b - 1) : null;
        }

        public final int b() {
            return this.b != null ? this.b.size() : 0;
        }
    }

    e() {
        this.a = new a();
        this.b = new HashMap();
    }

    public final void a(K k, V v) {
        a aVar = (a) this.b.get(k);
        if (aVar == null) {
            aVar = new a(k);
            b(aVar);
            aVar.d = this.a.d;
            aVar.c = this.a;
            a(aVar);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        if (aVar.b == null) {
            aVar.b = new ArrayList();
        }
        aVar.b.add(v);
    }

    public final V a(K k) {
        a aVar = (a) this.b.get(k);
        if (aVar == null) {
            aVar = new a(k);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        b(aVar);
        aVar.d = this.a;
        aVar.c = this.a.c;
        a(aVar);
        return aVar.a();
    }

    public final V a() {
        for (a aVar = this.a.d; !aVar.equals(this.a); aVar = aVar.d) {
            V a = aVar.a();
            if (a != null) {
                return a;
            }
            b(aVar);
            this.b.remove(aVar.a);
            ((h) aVar.a).a();
        }
        return null;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("GroupedLinkedMap( ");
        Object obj = null;
        for (a aVar = this.a.c; !aVar.equals(this.a); aVar = aVar.c) {
            obj = 1;
            stringBuilder.append('{').append(aVar.a).append(':').append(aVar.b()).append("}, ");
        }
        if (obj != null) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        return stringBuilder.append(" )").toString();
    }

    private static <K, V> void a(a<K, V> aVar) {
        aVar.c.d = aVar;
        aVar.d.c = aVar;
    }

    private static <K, V> void b(a<K, V> aVar) {
        aVar.d.c = aVar.c;
        aVar.c.d = aVar.d;
    }
}
