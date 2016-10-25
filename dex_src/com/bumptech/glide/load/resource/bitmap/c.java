package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.h.h;
import com.bumptech.glide.load.engine.j;

// compiled from: BitmapResource.java
public final class c implements j<Bitmap> {
    private final Bitmap a;
    private final com.bumptech.glide.load.engine.a.c b;

    public static c a(Bitmap bitmap, com.bumptech.glide.load.engine.a.c cVar) {
        return bitmap == null ? null : new c(bitmap, cVar);
    }

    public c(Bitmap bitmap, com.bumptech.glide.load.engine.a.c cVar) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        } else if (cVar == null) {
            throw new NullPointerException("BitmapPool must not be null");
        } else {
            this.a = bitmap;
            this.b = cVar;
        }
    }

    public final int b() {
        return h.a(this.a);
    }

    public final void c() {
        if (!this.b.a(this.a)) {
            this.a.recycle();
        }
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return this.a;
    }
}
