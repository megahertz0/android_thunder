package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.load.engine.a.c;

// compiled from: GifBitmapProvider.java
final class a implements com.bumptech.glide.b.a.a {
    private final c a;

    public a(c cVar) {
        this.a = cVar;
    }

    public final Bitmap a(int i, int i2, Config config) {
        return this.a.b(i, i2, config);
    }

    public final void a(Bitmap bitmap) {
        if (!this.a.a(bitmap)) {
            bitmap.recycle();
        }
    }
}
