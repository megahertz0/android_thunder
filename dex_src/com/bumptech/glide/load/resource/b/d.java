package com.bumptech.glide.load.resource.b;

import com.bumptech.glide.e.b;
import com.bumptech.glide.load.b.p;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.j;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// compiled from: StreamFileDataLoadProvider.java
public final class d implements b<InputStream, File> {
    private static final a a;
    private final com.bumptech.glide.load.d<File, File> b;
    private final com.bumptech.glide.load.a<InputStream> c;

    // compiled from: StreamFileDataLoadProvider.java
    private static class a implements com.bumptech.glide.load.d<InputStream, File> {
        private a() {
        }

        public final String a() {
            return com.umeng.a.d;
        }

        public final /* synthetic */ j a(Object obj, int i, int i2) throws IOException {
            throw new Error("You cannot decode a File from an InputStream by default, try either #diskCacheStratey(DiskCacheStrategy.SOURCE) to avoid this call or #decoder(ResourceDecoder) to replace this Decoder");
        }
    }

    static {
        a = new a();
    }

    public d() {
        this.b = new a();
        this.c = new p();
    }

    public final com.bumptech.glide.load.d<File, File> a() {
        return this.b;
    }

    public final com.bumptech.glide.load.d<InputStream, File> b() {
        return a;
    }

    public final com.bumptech.glide.load.a<InputStream> c() {
        return this.c;
    }

    public final e<File> d() {
        return com.bumptech.glide.load.resource.b.b();
    }
}
