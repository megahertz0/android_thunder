package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.c.b;
import com.bumptech.glide.load.resource.c.e;

// compiled from: GifBitmapWrapperTransformation.java
public final class f implements com.bumptech.glide.load.f<a> {
    private final com.bumptech.glide.load.f<Bitmap> a;
    private final com.bumptech.glide.load.f<b> b;

    public f(c cVar, com.bumptech.glide.load.f<Bitmap> fVar) {
        this((com.bumptech.glide.load.f) fVar, new e(fVar, cVar));
    }

    private f(com.bumptech.glide.load.f<Bitmap> fVar, com.bumptech.glide.load.f<b> fVar2) {
        this.a = fVar;
        this.b = fVar2;
    }

    public final j<a> a(j<a> jVar, int i, int i2) {
        j jVar2 = ((a) jVar.a()).b;
        j jVar3 = ((a) jVar.a()).a;
        if (jVar2 != null && this.a != null) {
            j a = this.a.a(jVar2, i, i2);
            return !jVar2.equals(a) ? new b(new a(a, ((a) jVar.a()).a)) : jVar;
        } else if (jVar3 == null || this.b == null) {
            return jVar;
        } else {
            jVar2 = this.b.a(jVar3, i, i2);
            return !jVar3.equals(jVar2) ? new b(new a(((a) jVar.a()).b, jVar2)) : jVar;
        }
    }

    public final String a() {
        return this.a.a();
    }
}
