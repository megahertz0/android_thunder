package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.c.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

// compiled from: ImageLoaderEngine.java
final class f {
    final e a;
    Executor b;
    Executor c;
    Executor d;
    final Map<Integer, String> e;
    final AtomicBoolean f;
    final AtomicBoolean g;
    final AtomicBoolean h;
    final Object i;
    private final Map<String, ReentrantLock> j;

    f(e eVar) {
        this.e = Collections.synchronizedMap(new HashMap());
        this.j = new WeakHashMap();
        this.f = new AtomicBoolean(false);
        this.g = new AtomicBoolean(false);
        this.h = new AtomicBoolean(false);
        this.i = new Object();
        this.a = eVar;
        this.b = eVar.g;
        this.c = eVar.h;
        this.d = Executors.newCachedThreadPool(a.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, "uil-pool-d-"));
    }

    final void a() {
        if (!this.a.i && ((ExecutorService) this.b).isShutdown()) {
            this.b = b();
        }
        if (!this.a.j && ((ExecutorService) this.c).isShutdown()) {
            this.c = b();
        }
    }

    private Executor b() {
        return a.a(this.a.k, this.a.l, this.a.m);
    }

    final String a(a aVar) {
        return (String) this.e.get(Integer.valueOf(aVar.getId()));
    }

    final void b(a aVar) {
        this.e.remove(Integer.valueOf(aVar.getId()));
    }

    final ReentrantLock a(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.j.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        reentrantLock = new ReentrantLock();
        this.j.put(str, reentrantLock);
        return reentrantLock;
    }
}
