package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.e.b;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.i;
import com.bumptech.glide.load.b.j;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import java.io.File;
import java.io.InputStream;

// compiled from: ImageVideoDataLoadProvider.java
public final class p implements b<i, Bitmap> {
    private final o a;
    private final d<File, Bitmap> b;
    private final e<Bitmap> c;
    private final j d;

    public p(b<InputStream, Bitmap> bVar, b<ParcelFileDescriptor, Bitmap> bVar2) {
        this.c = bVar.d();
        this.d = new j(bVar.c(), bVar2.c());
        this.b = bVar.a();
        this.a = new o(bVar.b(), bVar2.b());
    }

    public final d<File, Bitmap> a() {
        return this.b;
    }

    public final d<i, Bitmap> b() {
        return this.a;
    }

    public final a<i> c() {
        return this.d;
    }

    public final e<Bitmap> d() {
        return this.c;
    }
}
