package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import com.bumptech.glide.b.a;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import java.io.IOException;

// compiled from: GifFrameResourceDecoder.java
final class h implements d<a, Bitmap> {
    private final c a;

    public final /* synthetic */ j a(Object obj, int i, int i2) throws IOException {
        return com.bumptech.glide.load.resource.bitmap.c.a(((a) obj).b(), this.a);
    }

    public h(c cVar) {
        this.a = cVar;
    }

    public final String a() {
        return "GifFrameResourceDecoder.com.bumptech.glide.load.resource.gif";
    }
}
