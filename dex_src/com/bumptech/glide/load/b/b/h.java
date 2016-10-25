package com.bumptech.glide.load.b.b;

import android.content.Context;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.e;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.s;
import java.io.InputStream;
import java.net.URL;

// compiled from: StreamUrlLoader.java
public final class h extends s<InputStream> {

    // compiled from: StreamUrlLoader.java
    public static class a implements n<URL, InputStream> {
        public final m<URL, InputStream> a(Context context, c cVar) {
            return new h(cVar.a(e.class, InputStream.class));
        }
    }

    public h(m<e, InputStream> mVar) {
        super(mVar);
    }
}
