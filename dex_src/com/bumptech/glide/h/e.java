package com.bumptech.glide.h;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

// compiled from: LruCache.java
public class e<T, Y> {
    private int a;
    public final LinkedHashMap<T, Y> b;
    public int c;
    private final int d;

    public e(int i) {
        this.b = new LinkedHashMap(100, 0.75f, true);
        this.c = 0;
        this.d = i;
        this.a = i;
    }

    public int a(Y y) {
        return 1;
    }

    public void a(T t, Y y) {
    }

    public final Y b(T t) {
        return this.b.get(t);
    }

    public final Y b(T t, Y y) {
        if (a(y) >= this.a) {
            a(t, y);
            return null;
        }
        Y put = this.b.put(t, y);
        if (y != null) {
            this.c += a(y);
        }
        if (put != null) {
            this.c -= a(put);
        }
        b(this.a);
        return put;
    }

    public final void a() {
        b(0);
    }

    public final void b(int i) {
        while (this.c > i) {
            Entry entry = (Entry) this.b.entrySet().iterator().next();
            Object value = entry.getValue();
            this.c -= a(value);
            Object key = entry.getKey();
            this.b.remove(key);
            a(key, value);
        }
    }
}
