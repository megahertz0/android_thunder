package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.e.b;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.p;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.resource.b.c;
import java.io.File;
import java.io.InputStream;

// compiled from: StreamBitmapDataLoadProvider.java
public final class r implements b<InputStream, Bitmap> {
    private final s a;
    private final b b;
    private final p c;
    private final c<Bitmap> d;

    public r(com.bumptech.glide.load.engine.a.c cVar, DecodeFormat decodeFormat) {
        this.c = new p();
        this.a = new s(cVar, decodeFormat);
        this.b = new b();
        this.d = new c(this.a);
    }

    public final d<File, Bitmap> a() {
        return this.d;
    }

    public final d<InputStream, Bitmap> b() {
        return this.a;
    }

    public final a<InputStream> c() {
        return this.c;
    }

    public final e<Bitmap> d() {
        return this.b;
    }
}
