package com.bumptech.glide.load.resource.c;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.h.h;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.a.c;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

// compiled from: GifResourceDecoder.java
public final class i implements d<InputStream, b> {
    private static final b a;
    private static final a b;
    private final Context c;
    private final b d;
    private final c e;
    private final a f;
    private final a g;

    // compiled from: GifResourceDecoder.java
    static class a {
        private final Queue<com.bumptech.glide.b.a> a;

        a() {
            this.a = h.a(0);
        }

        public final synchronized com.bumptech.glide.b.a a(com.bumptech.glide.b.a.a aVar) {
            com.bumptech.glide.b.a aVar2;
            aVar2 = (com.bumptech.glide.b.a) this.a.poll();
            if (aVar2 == null) {
                aVar2 = new com.bumptech.glide.b.a(aVar);
            }
            return aVar2;
        }

        public final synchronized void a(com.bumptech.glide.b.a aVar) {
            aVar.e = null;
            aVar.d = null;
            aVar.a = null;
            aVar.b = null;
            if (aVar.g != null) {
                aVar.f.a(aVar.g);
            }
            aVar.g = null;
            this.a.offer(aVar);
        }
    }

    // compiled from: GifResourceDecoder.java
    static class b {
        private final Queue<com.bumptech.glide.b.d> a;

        b() {
            this.a = h.a(0);
        }

        public final synchronized com.bumptech.glide.b.d a(byte[] bArr) {
            com.bumptech.glide.b.d dVar;
            dVar = (com.bumptech.glide.b.d) this.a.poll();
            if (dVar == null) {
                dVar = new com.bumptech.glide.b.d();
            }
            return dVar.a(bArr);
        }

        public final synchronized void a(com.bumptech.glide.b.d dVar) {
            dVar.a = null;
            dVar.b = null;
            this.a.offer(dVar);
        }
    }

    static {
        a = new b();
        b = new a();
    }

    public i(Context context, c cVar) {
        this(context, cVar, a, b);
    }

    private i(Context context, c cVar, b bVar, a aVar) {
        this.c = context;
        this.e = cVar;
        this.f = aVar;
        this.g = new a(cVar);
        this.d = bVar;
    }

    private d a(InputStream inputStream, int i, int i2) {
        d dVar = null;
        byte[] a = a(inputStream);
        com.bumptech.glide.b.d a2 = this.d.a(a);
        com.bumptech.glide.b.a a3 = this.f.a(this.g);
        com.bumptech.glide.b.c a4 = a2.a();
        if (a4.c > 0 && a4.b == 0) {
            a3.a(a4, a);
            a3.a();
            Bitmap b = a3.b();
            if (b != null) {
                dVar = new d(new b(this.c, this.g, this.e, com.bumptech.glide.load.resource.d.b(), i, i2, a4, a, b));
            }
        }
        this.d.a(a2);
        this.f.a(a3);
        return dVar;
    }

    public final String a() {
        return com.umeng.a.d;
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
