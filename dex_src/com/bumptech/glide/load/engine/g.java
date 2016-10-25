package com.bumptech.glide.load.engine;

import android.os.Looper;
import com.bumptech.glide.load.b;

// compiled from: EngineResource.java
public final class g<Z> implements j<Z> {
    final boolean a;
    a b;
    b c;
    private final j<Z> d;
    private int e;
    private boolean f;

    // compiled from: EngineResource.java
    static interface a {
        void b(b bVar, g<?> gVar);
    }

    public g(j<Z> jVar, boolean z) {
        if (jVar == null) {
            throw new NullPointerException("Wrapped resource must not be null");
        }
        this.d = jVar;
        this.a = z;
    }

    public final Z a() {
        return this.d.a();
    }

    public final int b() {
        return this.d.b();
    }

    public final void c() {
        if (this.e > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (this.f) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        } else {
            this.f = true;
            this.d.c();
        }
    }

    public final void d() {
        if (this.f) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        } else if (Looper.getMainLooper().equals(Looper.myLooper())) {
            this.e++;
        } else {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        }
    }

    public final void e() {
        if (this.e <= 0) {
            throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
        } else if (Looper.getMainLooper().equals(Looper.myLooper())) {
            int i = this.e - 1;
            this.e = i;
            if (i == 0) {
                this.b.b(this.c, this);
            }
        } else {
            throw new IllegalThreadStateException("Must call release on the main thread");
        }
    }
}
