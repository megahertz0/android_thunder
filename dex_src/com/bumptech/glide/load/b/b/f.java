package com.bumptech.glide.load.b.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.q;
import java.io.InputStream;

// compiled from: StreamStringLoader.java
public final class f extends q<InputStream> implements d<String> {

    // compiled from: StreamStringLoader.java
    public static class a implements n<String, InputStream> {
        public final m<String, InputStream> a(Context context, c cVar) {
            return new f(cVar.a(Uri.class, InputStream.class));
        }
    }

    public f(m<Uri, InputStream> mVar) {
        super(mVar);
    }
}
