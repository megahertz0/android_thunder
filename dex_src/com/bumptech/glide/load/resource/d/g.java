package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.e.b;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.i;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.a.c;
import java.io.File;
import java.io.InputStream;

// compiled from: ImageVideoGifDrawableLoadProvider.java
public final class g implements b<i, a> {
    private final d<File, a> a;
    private final d<i, a> b;
    private final e<a> c;
    private final a<i> d;

    public g(b<i, Bitmap> bVar, b<InputStream, com.bumptech.glide.load.resource.c.b> bVar2, c cVar) {
        d cVar2 = new c(bVar.b(), bVar2.b(), cVar);
        this.a = new com.bumptech.glide.load.resource.b.c(new e(cVar2));
        this.b = cVar2;
        this.c = new d(bVar.d(), bVar2.d());
        this.d = bVar.c();
    }

    public final d<File, a> a() {
        return this.a;
    }

    public final d<i, a> b() {
        return this.b;
    }

    public final a<i> c() {
        return this.d;
    }

    public final e<a> d() {
        return this.c;
    }
}
