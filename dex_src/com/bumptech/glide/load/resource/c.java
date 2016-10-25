package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.engine.j;

// compiled from: SimpleResource.java
public class c<T> implements j<T> {
    protected final T a;

    public c(T t) {
        if (t == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.a = t;
    }

    public final T a() {
        return this.a;
    }

    public final int b() {
        return 1;
    }

    public final void c() {
    }
}
