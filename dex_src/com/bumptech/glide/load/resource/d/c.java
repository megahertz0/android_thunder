package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.b.i;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.resource.bitmap.q;
import com.taobao.accs.data.Message;
import java.io.IOException;
import java.io.InputStream;

// compiled from: GifBitmapWrapperResourceDecoder.java
public final class c implements d<i, a> {
    private static final b a;
    private static final a b;
    private final d<i, Bitmap> c;
    private final d<InputStream, com.bumptech.glide.load.resource.c.b> d;
    private final com.bumptech.glide.load.engine.a.c e;
    private final b f;
    private final a g;
    private String h;

    // compiled from: GifBitmapWrapperResourceDecoder.java
    static class a {
        a() {
        }
    }

    // compiled from: GifBitmapWrapperResourceDecoder.java
    static class b {
        b() {
        }
    }

    static {
        a = new b();
        b = new a();
    }

    public c(d<i, Bitmap> dVar, d<InputStream, com.bumptech.glide.load.resource.c.b> dVar2, com.bumptech.glide.load.engine.a.c cVar) {
        this(dVar, dVar2, cVar, a, b);
    }

    private c(d<i, Bitmap> dVar, d<InputStream, com.bumptech.glide.load.resource.c.b> dVar2, com.bumptech.glide.load.engine.a.c cVar, b bVar, a aVar) {
        this.c = dVar;
        this.d = dVar2;
        this.e = cVar;
        this.f = bVar;
        this.g = aVar;
    }

    private j<a> a(i iVar, int i, int i2) throws IOException {
        a aVar;
        com.bumptech.glide.h.a a = com.bumptech.glide.h.a.a();
        byte[] b = a.b();
        if (iVar.a != null) {
            InputStream qVar = new q(iVar.a, b);
            qVar.mark(Message.FLAG_RET);
            ImageType a2 = new ImageHeaderParser(qVar).a();
            qVar.reset();
            if (a2 == ImageType.GIF) {
                j a3 = this.d.a(qVar, i, i2);
                if (a3 != null) {
                    com.bumptech.glide.load.resource.c.b bVar = (com.bumptech.glide.load.resource.c.b) a3.a();
                    if (bVar.b.e.c > 1) {
                        aVar = new a(null, a3);
                    } else {
                        aVar = new a(new com.bumptech.glide.load.resource.bitmap.c(bVar.a.i, this.e), null);
                    }
                    if (aVar == null) {
                        aVar = b(new i(qVar, iVar.b), i, i2);
                    }
                }
            }
            aVar = null;
            if (aVar == null) {
                aVar = b(new i(qVar, iVar.b), i, i2);
            }
        } else {
            aVar = b(iVar, i, i2);
        }
        a.a(b);
        return aVar != null ? new b(aVar) : null;
    }

    private a b(i iVar, int i, int i2) throws IOException {
        j a = this.c.a(iVar, i, i2);
        return a != null ? new a(a, null) : null;
    }

    public final String a() {
        if (this.h == null) {
            this.h = this.d.a() + this.c.a();
        }
        return this.h;
    }
}
