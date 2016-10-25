package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.h.d;
import com.bumptech.glide.load.f;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// compiled from: DecodeJob.java
public final class a<A, T, Z> {
    private static final b e;
    final e a;
    public final com.bumptech.glide.load.a.c<A> b;
    final DiskCacheStrategy c;
    public volatile boolean d;
    private final int f;
    private final int g;
    private final com.bumptech.glide.e.b<A, T> h;
    private final f<T> i;
    private final com.bumptech.glide.load.resource.e.c<T, Z> j;
    private final a k;
    private final Priority l;
    private final b m;

    // compiled from: DecodeJob.java
    static interface a {
        com.bumptech.glide.load.engine.b.a a();
    }

    // compiled from: DecodeJob.java
    static class b {
        b() {
        }
    }

    // compiled from: DecodeJob.java
    class c<DataType> implements com.bumptech.glide.load.engine.b.a.b {
        private final com.bumptech.glide.load.a<DataType> b;
        private final DataType c;

        public c(com.bumptech.glide.load.a<DataType> aVar, DataType dataType) {
            this.b = aVar;
            this.c = dataType;
        }

        public final boolean a(File file) {
            boolean z = false;
            OutputStream outputStream = null;
            try {
                a.this.m;
                OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                try {
                    z = this.b.a(this.c, bufferedOutputStream);
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                    }
                } catch (FileNotFoundException e2) {
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    return z;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    outputStream = bufferedOutputStream;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th2;
                }
            } catch (FileNotFoundException e3) {
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                return z;
            } catch (Throwable th3) {
                th2 = th3;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e5) {
                    }
                }
                throw th2;
            }
            return z;
        }
    }

    static {
        e = new b();
    }

    public a(e eVar, int i, int i2, com.bumptech.glide.load.a.c<A> cVar, com.bumptech.glide.e.b<A, T> bVar, f<T> fVar, com.bumptech.glide.load.resource.e.c<T, Z> cVar2, a aVar, DiskCacheStrategy diskCacheStrategy, Priority priority) {
        this(eVar, i, i2, cVar, bVar, fVar, cVar2, aVar, diskCacheStrategy, priority, e);
    }

    private a(e eVar, int i, int i2, com.bumptech.glide.load.a.c<A> cVar, com.bumptech.glide.e.b<A, T> bVar, f<T> fVar, com.bumptech.glide.load.resource.e.c<T, Z> cVar2, a aVar, DiskCacheStrategy diskCacheStrategy, Priority priority, b bVar2) {
        this.a = eVar;
        this.f = i;
        this.g = i2;
        this.b = cVar;
        this.h = bVar;
        this.i = fVar;
        this.j = cVar2;
        this.k = aVar;
        this.c = diskCacheStrategy;
        this.l = priority;
        this.m = bVar2;
    }

    final j<Z> a(j<T> jVar) {
        j jVar2;
        long a = d.a();
        if (jVar == null) {
            jVar2 = null;
        } else {
            jVar2 = this.i.a(jVar, this.f, this.g);
            if (!jVar.equals(jVar2)) {
                jVar.c();
            }
        }
        if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            a("Transformed resource from source", a);
        }
        if (jVar2 != null && this.c.cacheResult()) {
            a = d.a();
            this.k.a().a(this.a, new c(this, this.h.d(), jVar2));
            if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                a("Wrote transformed from source to cache", a);
            }
        }
        a = d.a();
        j<Z> b = b(jVar2);
        if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            a("Transcoded transformed from source", a);
        }
        return b;
    }

    final j<T> a() throws Exception {
        long a = d.a();
        Object a2 = this.b.a(this.l);
        if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            a("Fetched data", a);
        }
        if (this.d) {
            this.b.a();
            return null;
        }
        j<T> a3;
        if (this.c.cacheSource()) {
            a = d.a();
            this.k.a().a(this.a.a(), new c(this, this.h.c(), a2));
            if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                a("Wrote source to cache", a);
            }
            long a4 = d.a();
            a3 = a(this.a.a());
            if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE) && a3 != null) {
                a("Decoded source from cache", a4);
            }
        } else {
            long a5 = d.a();
            a3 = this.h.b().a(a2, this.f, this.g);
            if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                a("Decoded from source", a5);
            }
        }
        this.b.a();
        return a3;
    }

    final j<T> a(com.bumptech.glide.load.b bVar) throws IOException {
        File a = this.k.a().a(bVar);
        if (a == null) {
            return null;
        }
        j<T> a2 = this.h.a().a(a, this.f, this.g);
        if (a2 != null) {
            return a2;
        }
        this.k.a().b(bVar);
        return a2;
    }

    final j<Z> b(j<T> jVar) {
        return jVar == null ? null : this.j.a(jVar);
    }

    final void a(String str, long j) {
        new StringBuilder().append(str).append(" in ").append(d.a(j)).append(", key: ").append(this.a);
    }
}
