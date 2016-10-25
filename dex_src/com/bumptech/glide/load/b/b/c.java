package com.bumptech.glide.load.b.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.b.b;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import java.io.File;
import java.io.InputStream;

// compiled from: StreamFileLoader.java
public final class c extends b<InputStream> implements d<File> {

    // compiled from: StreamFileLoader.java
    public static class a implements n<File, InputStream> {
        public final m<File, InputStream> a(Context context, com.bumptech.glide.load.b.c cVar) {
            return new c(cVar.a(Uri.class, InputStream.class));
        }
    }

    public c(m<Uri, InputStream> mVar) {
        super(mVar);
    }
}
