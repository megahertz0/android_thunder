package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.c.b;
import java.io.OutputStream;

// compiled from: GifBitmapWrapperResourceEncoder.java
public final class d implements e<a> {
    private final e<Bitmap> a;
    private final e<b> b;
    private String c;

    public final /* bridge */ /* synthetic */ boolean a(Object obj, OutputStream outputStream) {
        a aVar = (a) ((j) obj).a();
        j jVar = aVar.b;
        return jVar != null ? this.a.a(jVar, outputStream) : this.b.a(aVar.a, outputStream);
    }

    public d(e<Bitmap> eVar, e<b> eVar2) {
        this.a = eVar;
        this.b = eVar2;
    }

    public final String a() {
        if (this.c == null) {
            this.c = this.a.a() + this.b.a();
        }
        return this.c;
    }
}
