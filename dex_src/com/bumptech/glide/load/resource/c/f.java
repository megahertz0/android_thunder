package com.bumptech.glide.load.resource.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.e;
import com.bumptech.glide.f.b.g;
import com.bumptech.glide.load.b.m;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

// compiled from: GifFrameLoader.java
final class f {
    final b a;
    final Handler b;
    boolean c;
    boolean d;
    com.bumptech.glide.c<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> e;
    a f;
    boolean g;
    private final com.bumptech.glide.b.a h;

    // compiled from: GifFrameLoader.java
    public static interface b {
        void b(int i);
    }

    // compiled from: GifFrameLoader.java
    static class a extends g<Bitmap> {
        final int a;
        Bitmap b;
        private final Handler c;
        private final long d;

        public final /* synthetic */ void a(Object obj, com.bumptech.glide.f.a.c cVar) {
            this.b = (Bitmap) obj;
            this.c.sendMessageAtTime(this.c.obtainMessage(1, this), this.d);
        }

        public a(Handler handler, int i, long j) {
            this.c = handler;
            this.a = i;
            this.d = j;
        }
    }

    // compiled from: GifFrameLoader.java
    private class c implements Callback {
        private c() {
        }

        public final boolean handleMessage(Message message) {
            if (message.what == 1) {
                a aVar = (a) message.obj;
                f fVar = f.this;
                if (fVar.g) {
                    fVar.b.obtainMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE, aVar).sendToTarget();
                } else {
                    a aVar2 = fVar.f;
                    fVar.f = aVar;
                    f.this.b(f.this);
                    if (aVar2 != null) {
                        fVar.b.obtainMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE, aVar2).sendToTarget();
                    }
                    fVar.d = false;
                    fVar.b();
                }
                return true;
            }
            if (message.what == 2) {
                e.a((a) message.obj);
            }
            return false;
        }
    }

    // compiled from: GifFrameLoader.java
    static class d implements com.bumptech.glide.load.b {
        private final UUID a;

        public d() {
            this(UUID.randomUUID());
        }

        private d(UUID uuid) {
            this.a = uuid;
        }

        public final boolean equals(Object obj) {
            return obj instanceof d ? ((d) obj).a.equals(this.a) : false;
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        public final void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    public f(Context context, b bVar, com.bumptech.glide.b.a aVar, int i, int i2) {
        com.bumptech.glide.load.d hVar = new h(e.a(context).b);
        m gVar = new g();
        com.bumptech.glide.load.a b = com.bumptech.glide.load.resource.a.b();
        com.bumptech.glide.g.a.a aVar2 = new com.bumptech.glide.g.a.a(new com.bumptech.glide.g.a(e.b(context), gVar, com.bumptech.glide.b.a.class), aVar);
        com.bumptech.glide.d dVar = new com.bumptech.glide.d(aVar2.d.c.a, aVar2.d.c.d, aVar2.b, aVar2.d.a, aVar2.d.b, Bitmap.class, aVar2.d.c.c, aVar2.d.c.b, aVar2.d.c.e);
        if (aVar2.c) {
            dVar.b(aVar2.a);
        }
        this(bVar, aVar, dVar.a(b).a(hVar).a(true).b(DiskCacheStrategy.NONE).a(i, i2));
    }

    private f(b bVar, com.bumptech.glide.b.a aVar, com.bumptech.glide.c<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> cVar) {
        this.c = false;
        this.d = false;
        Handler handler = new Handler(Looper.getMainLooper(), new c());
        this.a = bVar;
        this.h = aVar;
        this.b = handler;
        this.e = cVar;
    }

    final void b() {
        if (this.c && !this.d) {
            int i;
            this.d = true;
            this.h.a();
            long uptimeMillis = SystemClock.uptimeMillis();
            com.bumptech.glide.b.a aVar = this.h;
            if (aVar.e.c <= 0 || aVar.c < 0) {
                i = -1;
            } else {
                i = aVar.a(aVar.c);
            }
            this.e.b(new d()).a(new a(this.b, this.h.c, ((long) i) + uptimeMillis));
        }
    }

    public final void a() {
        this.c = false;
        if (this.f != null) {
            e.a(this.f);
            this.f = null;
        }
        this.g = true;
    }
}
