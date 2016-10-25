package com.bumptech.glide.load.resource.d;

import com.bumptech.glide.load.b.i;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.j;
import java.io.IOException;
import java.io.InputStream;

// compiled from: GifBitmapWrapperStreamResourceDecoder.java
public final class e implements d<InputStream, a> {
    private final d<i, a> a;

    public final /* synthetic */ j a(Object obj, int i, int i2) throws IOException {
        return this.a.a(new i((InputStream) obj, null), i, i2);
    }

    public e(d<i, a> dVar) {
        this.a = dVar;
    }

    public final String a() {
        return this.a.a();
    }
}
