package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.f;

// compiled from: GifDrawableTransformation.java
public final class e implements f<b> {
    private final f<Bitmap> a;
    private final c b;

    public e(f<Bitmap> fVar, c cVar) {
        this.a = fVar;
        this.b = cVar;
    }

    public final j<b> a(j<b> jVar, int i, int i2) {
        b bVar = (b) jVar.a();
        j cVar = new com.bumptech.glide.load.resource.bitmap.c(((b) jVar.a()).a.i, this.b);
        j a = this.a.a(cVar, i, i2);
        if (!cVar.equals(a)) {
            cVar.c();
        }
        Bitmap bitmap = (Bitmap) a.a();
        f fVar = this.a;
        if (bitmap == null) {
            throw new NullPointerException("The first frame of the GIF must not be null");
        } else if (fVar == null) {
            throw new NullPointerException("The frame transformation must not be null");
        } else {
            bVar.a.d = fVar;
            bVar.a.i = bitmap;
            f fVar2 = bVar.c;
            if (fVar == null) {
                throw new NullPointerException("Transformation must not be null");
            }
            fVar2.e = fVar2.e.a(fVar);
            return jVar;
        }
    }

    public final String a() {
        return this.a.a();
    }
}
