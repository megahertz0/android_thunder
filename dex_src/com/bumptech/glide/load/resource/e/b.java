package com.bumptech.glide.load.resource.e;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.bitmap.m;
import com.bumptech.glide.load.resource.bitmap.n;

// compiled from: GlideBitmapDrawableTranscoder.java
public final class b implements c<Bitmap, m> {
    private final Resources a;
    private final c b;

    public b(Resources resources, c cVar) {
        this.a = resources;
        this.b = cVar;
    }

    public final j<m> a(j<Bitmap> jVar) {
        return new n(new m(this.a, (Bitmap) jVar.a()), this.b);
    }

    public final String a() {
        return "GlideBitmapDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
