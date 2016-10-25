package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.c.b;

// compiled from: GifBitmapWrapper.java
public class a {
    public final j<b> a;
    public final j<Bitmap> b;

    public a(j<Bitmap> jVar, j<b> jVar2) {
        if (jVar != null && jVar2 != null) {
            throw new IllegalArgumentException("Can only contain either a bitmap resource or a gif resource, not both");
        } else if (jVar == null && jVar2 == null) {
            throw new IllegalArgumentException("Must contain either a bitmap resource or a gif resource");
        } else {
            this.b = jVar;
            this.a = jVar2;
        }
    }
}
