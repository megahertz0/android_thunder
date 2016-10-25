package com.bumptech.glide.e;

import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.resource.e.c;
import java.io.File;

// compiled from: ChildLoadProvider.java
public final class a<A, T, Z, R> implements f<A, T, Z, R>, Cloneable {
    public d<T, Z> a;
    public com.bumptech.glide.load.a<T> b;
    private final f<A, T, Z, R> c;
    private d<File, Z> d;
    private e<Z> e;
    private c<Z, R> f;

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    public a(f<A, T, Z, R> fVar) {
        this.c = fVar;
    }

    public final m<A, T> e() {
        return this.c.e();
    }

    public final d<File, Z> a() {
        return this.d != null ? this.d : this.c.a();
    }

    public final d<T, Z> b() {
        return this.a != null ? this.a : this.c.b();
    }

    public final com.bumptech.glide.load.a<T> c() {
        return this.b != null ? this.b : this.c.c();
    }

    public final e<Z> d() {
        return this.e != null ? this.e : this.c.d();
    }

    public final c<Z, R> f() {
        return this.f != null ? this.f : this.c.f();
    }

    public final a<A, T, Z, R> g() {
        try {
            return (a) super.clone();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
