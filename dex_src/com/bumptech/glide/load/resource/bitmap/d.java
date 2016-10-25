package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.h.h;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.f;

// compiled from: BitmapTransformation.java
public abstract class d implements f<Bitmap> {
    private c a;

    protected abstract Bitmap a(c cVar, Bitmap bitmap, int i, int i2);

    public d(c cVar) {
        this.a = cVar;
    }

    public final j<Bitmap> a(j<Bitmap> jVar, int i, int i2) {
        if (h.a(i, i2)) {
            Bitmap bitmap = (Bitmap) jVar.a();
            if (i == Integer.MIN_VALUE) {
                i = bitmap.getWidth();
            }
            if (i2 == Integer.MIN_VALUE) {
                i2 = bitmap.getHeight();
            }
            Bitmap a = a(this.a, bitmap, i, i2);
            return !bitmap.equals(a) ? c.a(a, this.a) : jVar;
        } else {
            throw new IllegalArgumentException(new StringBuilder("Cannot apply transformation on width: ").append(i).append(" or height: ").append(i2).append(" less than or equal to zero and not Target.SIZE_ORIGINAL").toString());
        }
    }
}
