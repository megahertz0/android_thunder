package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.f.e;
import com.bumptech.glide.load.engine.executor.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: EngineRunnable.java
public final class h implements d, Runnable {
    public final a<?, ?, ?> a;
    public volatile boolean b;
    private final Priority c;
    private final a d;
    private int e;

    // compiled from: EngineRunnable.java
    static interface a extends e {
        void a(h hVar);
    }

    // compiled from: EngineRunnable.java
    private enum b {
        ;

        static {
            a = 1;
            b = 2;
            c = new int[]{a, b};
        }
    }

    public h(a aVar, a<?, ?, ?> aVar2, Priority priority) {
        this.d = aVar;
        this.a = aVar2;
        this.e = b.a;
        this.c = priority;
    }

    public final void run() {
        Exception exception = null;
        if (!this.b) {
            j c;
            try {
                if (b()) {
                    c = c();
                } else {
                    a aVar = this.a;
                    c = aVar.a(aVar.a());
                }
            } catch (Exception e) {
                exception = e;
                Exception e2 = null;
            }
            if (this.b) {
                if (c != null) {
                    c.c();
                }
            } else if (c != null) {
                this.d.a(c);
            } else if (b()) {
                this.e = b.b;
                this.d.a(this);
            } else {
                this.d.a(exception);
            }
        }
    }

    private boolean b() {
        return this.e == b.a;
    }

    private j<?> c() throws Exception {
        try {
            j a;
            a aVar = this.a;
            if (aVar.c.cacheResult()) {
                long a2 = com.bumptech.glide.h.d.a();
                a = aVar.a(aVar.a);
                if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    aVar.a("Decoded transformed from cache", a2);
                }
                a2 = com.bumptech.glide.h.d.a();
                a = aVar.b(a);
                if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    aVar.a("Transcoded transformed from cache", a2);
                }
            } else {
                a = null;
            }
        } catch (Exception e) {
            if (Log.isLoggable("EngineRunnable", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                new StringBuilder("Exception decoding result from cache: ").append(e);
            }
            a = null;
        }
        if (r0 != null) {
            return r0;
        }
        a aVar2 = this.a;
        if (!aVar2.c.cacheSource()) {
            return null;
        }
        long a3 = com.bumptech.glide.h.d.a();
        j a4 = aVar2.a(aVar2.a.a());
        if (Log.isLoggable("DecodeJob", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            aVar2.a("Decoded source from cache", a3);
        }
        return aVar2.a(a4);
    }

    public final int a() {
        return this.c.ordinal();
    }
}
