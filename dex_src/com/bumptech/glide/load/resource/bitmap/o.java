package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.b.i;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.j;
import java.io.IOException;
import java.io.InputStream;

// compiled from: ImageVideoBitmapDecoder.java
public final class o implements d<i, Bitmap> {
    private final d<InputStream, Bitmap> a;
    private final d<ParcelFileDescriptor, Bitmap> b;

    public o(d<InputStream, Bitmap> dVar, d<ParcelFileDescriptor, Bitmap> dVar2) {
        this.a = dVar;
        this.b = dVar2;
    }

    private j<Bitmap> a(i iVar, int i, int i2) throws IOException {
        j<Bitmap> jVar = null;
        InputStream inputStream = iVar.a;
        if (inputStream != null) {
            try {
                jVar = this.a.a(inputStream, i, i2);
            } catch (IOException e) {
            }
        }
        if (jVar != null) {
            return jVar;
        }
        ParcelFileDescriptor parcelFileDescriptor = iVar.b;
        return parcelFileDescriptor != null ? this.b.a(parcelFileDescriptor, i, i2) : jVar;
    }

    public final String a() {
        return "ImageVideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }
}
