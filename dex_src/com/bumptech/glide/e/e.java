package com.bumptech.glide.e;

import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.resource.e.c;
import java.io.File;

// compiled from: FixedLoadProvider.java
public final class e<A, T, Z, R> implements f<A, T, Z, R> {
    private final m<A, T> a;
    private final c<Z, R> b;
    private final b<T, Z> c;

    public e(m<A, T> mVar, c<Z, R> cVar, b<T, Z> bVar) {
        if (mVar == null) {
            throw new NullPointerException("ModelLoader must not be null");
        }
        this.a = mVar;
        if (cVar == null) {
            throw new NullPointerException("Transcoder must not be null");
        }
        this.b = cVar;
        if (bVar == null) {
            throw new NullPointerException("DataLoadProvider must not be null");
        }
        this.c = bVar;
    }

    public final m<A, T> e() {
        return this.a;
    }

    public final c<Z, R> f() {
        return this.b;
    }

    public final d<File, Z> a() {
        return this.c.a();
    }

    public final d<T, Z> b() {
        return this.c.b();
    }

    public final a<T> c() {
        return this.c.c();
    }

    public final com.bumptech.glide.load.e<Z> d() {
        return this.c.d();
    }
}
