package com.bumptech.glide.load.resource.c;

import android.content.Context;
import com.bumptech.glide.e.b;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.p;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import java.io.File;
import java.io.InputStream;

// compiled from: GifDrawableLoadProvider.java
public final class c implements b<InputStream, b> {
    private final i a;
    private final j b;
    private final p c;
    private final com.bumptech.glide.load.resource.b.c<b> d;

    public c(Context context, com.bumptech.glide.load.engine.a.c cVar) {
        this.a = new i(context, cVar);
        this.d = new com.bumptech.glide.load.resource.b.c(this.a);
        this.b = new j(cVar);
        this.c = new p();
    }

    public final d<File, b> a() {
        return this.d;
    }

    public final d<InputStream, b> b() {
        return this.a;
    }

    public final a<InputStream> c() {
        return this.c;
    }

    public final e<b> d() {
        return this.b;
    }
}
