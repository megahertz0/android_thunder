package com.bumptech.glide.load.b.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.o;
import java.io.InputStream;

// compiled from: StreamResourceLoader.java
public final class e extends o<InputStream> implements d<Integer> {

    // compiled from: StreamResourceLoader.java
    public static class a implements n<Integer, InputStream> {
        public final m<Integer, InputStream> a(Context context, c cVar) {
            return new e(context, cVar.a(Uri.class, InputStream.class));
        }
    }

    public e(Context context, m<Uri, InputStream> mVar) {
        super(context, (m) mVar);
    }
}
