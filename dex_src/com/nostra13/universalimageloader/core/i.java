package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.assist.c;
import com.nostra13.universalimageloader.core.d.b;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

// compiled from: LoadAndDisplayImageTask.java
final class i implements com.nostra13.universalimageloader.b.b.a, Runnable {
    final e a;
    final String b;
    final com.nostra13.universalimageloader.core.c.a c;
    final c d;
    final a e;
    final b f;
    private final f g;
    private final h h;
    private final Handler i;
    private final ImageDownloader j;
    private final ImageDownloader k;
    private final ImageDownloader l;
    private final com.nostra13.universalimageloader.core.a.b m;
    private final String n;
    private final c o;
    private final boolean p;
    private LoadedFrom q;

    // compiled from: LoadAndDisplayImageTask.java
    class a extends Exception {
        a() {
        }
    }

    public i(f fVar, h hVar, Handler handler) {
        this.q = LoadedFrom.NETWORK;
        this.g = fVar;
        this.h = hVar;
        this.i = handler;
        this.a = fVar.a;
        this.j = this.a.p;
        this.k = this.a.s;
        this.l = this.a.t;
        this.m = this.a.q;
        this.b = hVar.a;
        this.n = hVar.b;
        this.c = hVar.c;
        this.o = hVar.d;
        this.d = hVar.e;
        this.e = hVar.f;
        this.f = hVar.g;
        this.p = this.d.s;
    }

    public final void run() {
        int i = 1;
        if (!a() && !b()) {
            ReentrantLock reentrantLock = this.h.h;
            com.nostra13.universalimageloader.b.c.a("Start display image task [%s]", this.n);
            if (reentrantLock.isLocked()) {
                com.nostra13.universalimageloader.b.c.a("Image already is loading. Waiting... [%s]", this.n);
            }
            reentrantLock.lock();
            try {
                g();
                Bitmap a = this.a.n.a(this.n);
                if (a == null || a.isRecycled()) {
                    a = c();
                    if (a == null) {
                        reentrantLock.unlock();
                        return;
                    }
                    g();
                    k();
                    if (this.d.o == null) {
                        i = 0;
                    }
                    if (i != 0) {
                        com.nostra13.universalimageloader.b.c.a("PreProcess image before caching in memory [%s]", this.n);
                        a = this.d.o.a();
                        if (a == null) {
                            com.nostra13.universalimageloader.b.c.d("Pre-processor returned null [%s]", this.n);
                        }
                    }
                    if (a != null && this.d.h) {
                        com.nostra13.universalimageloader.b.c.a("Cache image in memory [%s]", this.n);
                        this.a.n.a(this.n, a);
                    }
                } else {
                    this.q = LoadedFrom.MEMORY_CACHE;
                    com.nostra13.universalimageloader.b.c.a("...Get cached bitmap from memory after waiting. [%s]", this.n);
                }
                if (a != null && this.d.a()) {
                    com.nostra13.universalimageloader.b.c.a("PostProcess image before displaying [%s]", this.n);
                    a = this.d.p.a();
                    if (a == null) {
                        com.nostra13.universalimageloader.b.c.d("Post-processor returned null [%s]", this.n);
                    }
                }
                g();
                k();
                reentrantLock.unlock();
                a(new b(a, this.h, this.g, this.q), this.p, this.i, this.g);
            } catch (a e) {
                if (!(this.p || l())) {
                    a(new l(this), false, this.i, this.g);
                }
                reentrantLock.unlock();
            }
        }
    }

    private boolean a() {
        AtomicBoolean atomicBoolean = this.g.f;
        if (atomicBoolean.get()) {
            synchronized (this.g.i) {
                if (atomicBoolean.get()) {
                    com.nostra13.universalimageloader.b.c.a("ImageLoader is paused. Waiting...  [%s]", this.n);
                    try {
                        this.g.i.wait();
                        com.nostra13.universalimageloader.b.c.a(".. Resume loading [%s]", this.n);
                    } catch (InterruptedException e) {
                        com.nostra13.universalimageloader.b.c.d("Task was interrupted [%s]", this.n);
                        return true;
                    }
                }
            }
        }
        return h();
    }

    private boolean b() {
        boolean z = true;
        if (this.d.l > 0) {
            boolean z2 = true;
        } else {
            int i = 0;
        }
        if (!z2) {
            return false;
        }
        com.nostra13.universalimageloader.b.c.a("Delay %d ms before loading...  [%s]", Integer.valueOf(this.d.l), this.n);
        try {
            Thread.sleep((long) this.d.l);
            z = h();
            return z;
        } catch (InterruptedException e) {
            Object[] objArr = new Object[z];
            objArr[0] = this.n;
            com.nostra13.universalimageloader.b.c.d("Task was interrupted [%s]", objArr);
            return z;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap c() throws com.nostra13.universalimageloader.core.i.a {
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.i.c():android.graphics.Bitmap");
        /*
        this = this;
        r1 = 0;
        r0 = r7.a;	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.o;	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = r7.b;	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.a(r2);	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r0 == 0) goto L_0x00d9;
    L_0x000d:
        r2 = r0.exists();	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r2 == 0) goto L_0x00d9;
    L_0x0013:
        r2 = r0.length();	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x00d9;
    L_0x001d:
        r2 = "Load image from disk cache [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r5 = r7.n;	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        com.nostra13.universalimageloader.b.c.a(r2, r3);	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.q = r2;	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.g();	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r2.wrap(r0);	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r7.a(r0);	 Catch:{ IllegalStateException -> 0x00a0, a -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
    L_0x0040:
        if (r0 == 0) goto L_0x004e;
    L_0x0042:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x004e;
    L_0x0048:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x004e:
        r2 = "Load image from network [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = 0;
        r5 = r7.n;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        com.nostra13.universalimageloader.b.c.a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.NETWORK;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r7.q = r2;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r7.b;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r7.d;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.i;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x0068:
        r3 = r7.d();	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x006e:
        r3 = r7.a;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.o;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = r7.b;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.a(r4);	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x007a:
        r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r2.wrap(r3);	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x0084:
        r7.g();	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r0 = r7.a(r2);	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r0 == 0) goto L_0x0099;
    L_0x008d:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x0099;
    L_0x0093:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x0099:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.DECODING_ERROR;	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = 0;
        r7.a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, a -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x009f:
        return r0;
    L_0x00a0:
        r0 = move-exception;
        r0 = r1;
    L_0x00a2:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.NETWORK_DENIED;
        r7.a(r2, r1);
        goto L_0x009f;
    L_0x00a8:
        r0 = move-exception;
        throw r0;
    L_0x00aa:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00ae:
        com.nostra13.universalimageloader.b.c.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.IO_ERROR;
        r7.a(r2, r1);
        goto L_0x009f;
    L_0x00b7:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00bb:
        com.nostra13.universalimageloader.b.c.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.OUT_OF_MEMORY;
        r7.a(r2, r1);
        goto L_0x009f;
    L_0x00c4:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00c8:
        com.nostra13.universalimageloader.b.c.a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.UNKNOWN;
        r7.a(r2, r1);
        goto L_0x009f;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x00c8;
    L_0x00d3:
        r1 = move-exception;
        goto L_0x00bb;
    L_0x00d5:
        r1 = move-exception;
        goto L_0x00ae;
    L_0x00d7:
        r2 = move-exception;
        goto L_0x00a2;
    L_0x00d9:
        r0 = r1;
        goto L_0x0040;
        */
    }

    private Bitmap a(String str) throws IOException {
        String str2 = str;
        return this.m.a(new com.nostra13.universalimageloader.core.a.c(this.n, str2, this.b, this.o, this.c.getScaleType(), f(), this.d));
    }

    private boolean d() throws a {
        com.nostra13.universalimageloader.b.c.a("Cache image on disk [%s]", this.n);
        try {
            boolean e = e();
            if (e) {
                int i = this.a.d;
                int i2 = this.a.e;
                if (i > 0 || i2 > 0) {
                    com.nostra13.universalimageloader.b.c.a("Resize image in disk cache [%s]", this.n);
                    File a = this.a.o.a(this.b);
                    if (a != null && a.exists()) {
                        c cVar = new c(i, i2);
                        com.nostra13.universalimageloader.core.c.a a2 = new com.nostra13.universalimageloader.core.c.a().a(this.d);
                        a2.j = ImageScaleType.IN_SAMPLE_INT;
                        Bitmap a3 = this.m.a(new com.nostra13.universalimageloader.core.a.c(this.n, Scheme.FILE.wrap(a.getAbsolutePath()), this.b, cVar, ViewScaleType.FIT_INSIDE, f(), a2.b()));
                        if (!(a3 == null || this.a.f == null)) {
                            com.nostra13.universalimageloader.b.c.a("Process image before cache on disk [%s]", this.n);
                            a3 = this.a.f.a();
                            if (a3 == null) {
                                com.nostra13.universalimageloader.b.c.d("Bitmap processor for disk cache returned null [%s]", this.n);
                            }
                        }
                        if (a3 != null) {
                            this.a.o.a(this.b, a3);
                            a3.recycle();
                        }
                    }
                }
            }
            return e;
        } catch (Throwable e2) {
            com.nostra13.universalimageloader.b.c.a(e2);
            return false;
        }
    }

    private boolean e() throws IOException {
        Closeable a = f().a(this.b, this.d.n);
        if (a == null) {
            com.nostra13.universalimageloader.b.c.d("No stream for image [%s]", this.n);
            return false;
        }
        boolean a2 = this.a.o.a(this.b, a, this);
        com.nostra13.universalimageloader.b.b.a(a);
        return a2;
    }

    public final boolean a(int i, int i2) {
        if (!this.p) {
            boolean z;
            if (l() || h()) {
                z = false;
            } else {
                if (this.f != null) {
                    a(new j(this, i, i2), false, this.i, this.g);
                }
                int i3 = 1;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private void a(FailType failType, Throwable th) {
        if (!this.p && !l() && !h()) {
            a(new k(this, failType, th), false, this.i, this.g);
        }
    }

    private ImageDownloader f() {
        if (this.g.g.get()) {
            return this.k;
        }
        return this.g.h.get() ? this.l : this.j;
    }

    private boolean h() {
        return i() || j();
    }

    private boolean i() {
        if (!this.c.isCollected()) {
            return false;
        }
        com.nostra13.universalimageloader.b.c.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.n);
        return true;
    }

    private boolean j() {
        if (this.n.equals(this.g.a(this.c))) {
            int i = 0;
        } else {
            boolean z = true;
        }
        if (!z) {
            return false;
        }
        com.nostra13.universalimageloader.b.c.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.n);
        return true;
    }

    private void k() throws a {
        if (l()) {
            throw new a();
        }
    }

    private boolean l() {
        if (!Thread.interrupted()) {
            return false;
        }
        com.nostra13.universalimageloader.b.c.a("Task was interrupted [%s]", this.n);
        return true;
    }

    static void a(Runnable runnable, boolean z, Handler handler, f fVar) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            fVar.d.execute(runnable);
        } else {
            handler.post(runnable);
        }
    }

    private void g() throws a {
        if (i()) {
            throw new a();
        } else if (j()) {
            throw new a();
        }
    }
}
