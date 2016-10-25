package com.xunlei.downloadprovider.service.downloads.b;

import android.os.SystemClock;

// compiled from: CachedVariable.java
public final class a<T> {
    protected T a;
    protected T b;
    private final long c;
    private long d;
    private long e;
    private boolean f;

    public a(T t, T t2) {
        this.d = 0;
        this.e = 0;
        this.f = false;
        this.a = t;
        this.b = t2;
        this.c = 10000;
    }

    public final T a() {
        return b(this.b);
    }

    public final synchronized void a(T t) {
        this.f = true;
        this.d = SystemClock.elapsedRealtime();
        this.a = t;
    }

    private synchronized T b(T t) {
        Object obj;
        this.e = SystemClock.elapsedRealtime();
        if (Math.abs(this.e - this.d) >= this.c) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            t = this.a;
        }
        return t;
    }
}
