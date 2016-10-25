package com.bumptech.glide.load.resource.d;

import com.bumptech.glide.load.engine.j;

// compiled from: GifBitmapWrapperResource.java
public final class b implements j<a> {
    private final a a;

    public b(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.a = aVar;
    }

    public final int b() {
        a aVar = this.a;
        return aVar.b != null ? aVar.b.b() : aVar.a.b();
    }

    public final void c() {
        j jVar = this.a.b;
        if (jVar != null) {
            jVar.c();
        }
        jVar = this.a.a;
        if (jVar != null) {
            jVar.c();
        }
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return this.a;
    }
}
