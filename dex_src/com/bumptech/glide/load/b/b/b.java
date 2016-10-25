package com.bumptech.glide.load.b.b;

import android.content.Context;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.b.n;
import java.io.InputStream;

// compiled from: StreamByteArrayLoader.java
public final class b implements d<byte[]> {
    private final String a;

    // compiled from: StreamByteArrayLoader.java
    public static class a implements n<byte[], InputStream> {
        public final m<byte[], InputStream> a(Context context, c cVar) {
            return new b();
        }
    }

    public final /* synthetic */ com.bumptech.glide.load.a.c a(Object obj, int i, int i2) {
        return new com.bumptech.glide.load.a.b((byte[]) obj, this.a);
    }

    public b() {
        this(com.umeng.a.d);
    }

    @Deprecated
    private b(String str) {
        this.a = str;
    }
}
