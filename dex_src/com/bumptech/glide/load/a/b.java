package com.bumptech.glide.load.a;

import com.bumptech.glide.Priority;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

// compiled from: ByteArrayFetcher.java
public final class b implements c<InputStream> {
    private final byte[] a;
    private final String b;

    public b(byte[] bArr, String str) {
        this.a = bArr;
        this.b = str;
    }

    public final void a() {
    }

    public final String b() {
        return this.b;
    }

    public final void c() {
    }

    public final /* synthetic */ Object a(Priority priority) throws Exception {
        return new ByteArrayInputStream(this.a);
    }
}
