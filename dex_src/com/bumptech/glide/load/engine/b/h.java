package com.bumptech.glide.load.engine.b;

import android.annotation.SuppressLint;
import com.bumptech.glide.h.e;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.b.i.a;
import com.bumptech.glide.load.engine.j;

// compiled from: LruResourceCache.java
public final class h extends e<b, j<?>> implements i {
    private a a;

    protected final /* synthetic */ int a(Object obj) {
        return ((j) obj).b();
    }

    public final /* synthetic */ j a(b bVar, j jVar) {
        return (j) super.b(bVar, jVar);
    }

    public h(int i) {
        super(i);
    }

    public final void a(a aVar) {
        this.a = aVar;
    }

    @SuppressLint({"InlinedApi"})
    public final void a(int i) {
        if (i >= 60) {
            b(0);
        } else if (i >= 40) {
            b(this.c / 2);
        }
    }

    public final /* synthetic */ j a(b bVar) {
        Object remove = this.b.remove(bVar);
        if (remove != null) {
            this.c -= a(remove);
        }
        return (j) remove;
    }
}
