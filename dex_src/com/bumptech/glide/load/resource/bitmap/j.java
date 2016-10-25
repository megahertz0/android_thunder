package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.e.b;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.a.c;
import java.io.File;

// compiled from: FileDescriptorBitmapDataLoadProvider.java
public final class j implements b<ParcelFileDescriptor, Bitmap> {
    private final d<File, Bitmap> a;
    private final k b;
    private final b c;
    private final a<ParcelFileDescriptor> d;

    public j(c cVar, DecodeFormat decodeFormat) {
        this.a = new com.bumptech.glide.load.resource.b.c(new s(cVar, decodeFormat));
        this.b = new k(cVar, decodeFormat);
        this.c = new b();
        this.d = com.bumptech.glide.load.resource.a.b();
    }

    public final d<File, Bitmap> a() {
        return this.a;
    }

    public final d<ParcelFileDescriptor, Bitmap> b() {
        return this.b;
    }

    public final a<ParcelFileDescriptor> c() {
        return this.d;
    }

    public final e<Bitmap> d() {
        return this.c;
    }
}
