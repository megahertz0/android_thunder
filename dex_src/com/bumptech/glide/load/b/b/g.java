package com.bumptech.glide.load.b.b;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.a.h;
import com.bumptech.glide.load.a.i;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.e;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.b.r;
import java.io.InputStream;

// compiled from: StreamUriLoader.java
public final class g extends r<InputStream> implements d<Uri> {

    // compiled from: StreamUriLoader.java
    public static class a implements n<Uri, InputStream> {
        public final m<Uri, InputStream> a(Context context, c cVar) {
            return new g(context, cVar.a(e.class, InputStream.class));
        }
    }

    public g(Context context, m<e, InputStream> mVar) {
        super(context, mVar);
    }

    protected final com.bumptech.glide.load.a.c<InputStream> a(Context context, Uri uri) {
        return new i(context, uri);
    }

    protected final com.bumptech.glide.load.a.c<InputStream> a(Context context, String str) {
        return new h(context.getApplicationContext().getAssets(), str);
    }
}
