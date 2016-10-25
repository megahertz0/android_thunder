package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import java.io.IOException;
import java.io.InputStream;

// compiled from: StreamBitmapDecoder.java
public final class s implements d<InputStream, Bitmap> {
    private final f a;
    private c b;
    private DecodeFormat c;
    private String d;

    public final /* bridge */ /* synthetic */ j a(Object obj, int i, int i2) throws IOException {
        return c.a(this.a.a((InputStream) obj, this.b, i, i2, this.c), this.b);
    }

    public s(c cVar, DecodeFormat decodeFormat) {
        this(f.a, cVar, decodeFormat);
    }

    private s(f fVar, c cVar, DecodeFormat decodeFormat) {
        this.a = fVar;
        this.b = cVar;
        this.c = decodeFormat;
    }

    public final String a() {
        if (this.d == null) {
            this.d = new StringBuilder("StreamBitmapDecoder.com.bumptech.glide.load.resource.bitmap").append(this.a.a()).append(this.c.name()).toString();
        }
        return this.d;
    }
}
