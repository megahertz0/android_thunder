package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.h.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.f;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.OutputStream;

// compiled from: GifResourceEncoder.java
public final class j implements e<b> {
    private static final a a;
    private final com.bumptech.glide.b.a.a b;
    private final c c;
    private final a d;

    // compiled from: GifResourceEncoder.java
    static class a {
        a() {
        }
    }

    static {
        a = new a();
    }

    public j(c cVar) {
        this(cVar, a);
    }

    private j(c cVar, a aVar) {
        this.c = cVar;
        this.b = new a(cVar);
        this.d = aVar;
    }

    private boolean a(com.bumptech.glide.load.engine.j<b> jVar, OutputStream outputStream) {
        long a = d.a();
        b bVar = (b) jVar.a();
        f fVar = bVar.a.d;
        if (fVar instanceof com.bumptech.glide.load.resource.d) {
            return a(bVar.a.b, outputStream);
        }
        byte[] bArr = bVar.a.b;
        com.bumptech.glide.b.d dVar = new com.bumptech.glide.b.d();
        dVar.a(bArr);
        com.bumptech.glide.b.c a2 = dVar.a();
        com.bumptech.glide.b.a aVar = new com.bumptech.glide.b.a(this.b);
        aVar.a(a2, bArr);
        aVar.a();
        com.bumptech.glide.c.a aVar2 = new com.bumptech.glide.c.a();
        if (!aVar2.a(outputStream)) {
            return false;
        }
        int i = 0;
        while (i < aVar.e.c) {
            com.bumptech.glide.load.engine.j cVar = new com.bumptech.glide.load.resource.bitmap.c(aVar.b(), this.c);
            com.bumptech.glide.load.engine.j a3 = fVar.a(cVar, bVar.getIntrinsicWidth(), bVar.getIntrinsicHeight());
            if (!cVar.equals(a3)) {
                cVar.c();
            }
            if (aVar2.a((Bitmap) a3.a())) {
                aVar2.a = Math.round(((float) aVar.a(aVar.c)) / 10.0f);
                aVar.a();
                a3.c();
                i++;
            } else {
                a3.c();
                return false;
            }
        }
        boolean a4 = aVar2.a();
        if (!Log.isLoggable("GifEncoder", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            return a4;
        }
        new StringBuilder("Encoded gif with ").append(aVar.e.c).append(" frames and ").append(bVar.a.b.length).append(" bytes in ").append(d.a(a)).append(" ms");
        return a4;
    }

    private static boolean a(byte[] bArr, OutputStream outputStream) {
        try {
            outputStream.write(bArr);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public final String a() {
        return com.umeng.a.d;
    }
}
