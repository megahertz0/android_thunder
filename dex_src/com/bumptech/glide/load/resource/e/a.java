package com.bumptech.glide.load.resource.e;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.load.resource.bitmap.m;

// compiled from: GifBitmapWrapperDrawableTranscoder.java
public final class a implements c<com.bumptech.glide.load.resource.d.a, b> {
    private final c<Bitmap, m> a;

    public a(c<Bitmap, m> cVar) {
        this.a = cVar;
    }

    public final j<b> a(j<com.bumptech.glide.load.resource.d.a> jVar) {
        com.bumptech.glide.load.resource.d.a aVar = (com.bumptech.glide.load.resource.d.a) jVar.a();
        j jVar2 = aVar.b;
        return jVar2 != null ? this.a.a(jVar2) : aVar.a;
    }

    public final String a() {
        return "GifBitmapWrapperDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
