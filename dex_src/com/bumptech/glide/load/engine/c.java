package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.f.e;
import com.bumptech.glide.h.h;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

// compiled from: EngineJob.java
public final class c implements a {
    private static final a k;
    private static final Handler l;
    public final List<e> a;
    public final d b;
    public final com.bumptech.glide.load.b c;
    public final ExecutorService d;
    public boolean e;
    public boolean f;
    public boolean g;
    public Set<e> h;
    public h i;
    public volatile Future<?> j;
    private final a m;
    private final ExecutorService n;
    private final boolean o;
    private j<?> p;
    private Exception q;
    private g<?> r;

    // compiled from: EngineJob.java
    static class a {
        a() {
        }
    }

    // compiled from: EngineJob.java
    private static class b implements Callback {
        private b() {
        }

        public final boolean handleMessage(Message message) {
            if (1 != message.what && 2 != message.what) {
                return false;
            }
            c cVar = (c) message.obj;
            if (1 == message.what) {
                c.a(cVar);
            } else {
                c.b(cVar);
            }
            return true;
        }
    }

    static {
        k = new a();
        l = new Handler(Looper.getMainLooper(), new b());
    }

    public c(com.bumptech.glide.load.b bVar, ExecutorService executorService, ExecutorService executorService2, boolean z, d dVar) {
        this(bVar, executorService, executorService2, z, dVar, k);
    }

    private c(com.bumptech.glide.load.b bVar, ExecutorService executorService, ExecutorService executorService2, boolean z, d dVar, a aVar) {
        this.a = new ArrayList();
        this.c = bVar;
        this.d = executorService;
        this.n = executorService2;
        this.o = z;
        this.b = dVar;
        this.m = aVar;
    }

    public final void a(h hVar) {
        this.j = this.n.submit(hVar);
    }

    public final void a(e eVar) {
        h.a();
        if (this.f) {
            eVar.a(this.r);
        } else if (this.g) {
            eVar.a(this.q);
        } else {
            this.a.add(eVar);
        }
    }

    private boolean b(e eVar) {
        return this.h != null && this.h.contains(eVar);
    }

    public final void a(j<?> jVar) {
        this.p = jVar;
        l.obtainMessage(1, this).sendToTarget();
    }

    public final void a(Exception exception) {
        this.q = exception;
        l.obtainMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE, this).sendToTarget();
    }

    static /* synthetic */ void a(c cVar) {
        if (cVar.e) {
            cVar.p.c();
        } else if (cVar.a.isEmpty()) {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        } else {
            cVar.r = new g(cVar.p, cVar.o);
            cVar.f = true;
            cVar.r.d();
            cVar.b.a(cVar.c, cVar.r);
            for (e eVar : cVar.a) {
                if (!cVar.b(eVar)) {
                    cVar.r.d();
                    eVar.a(cVar.r);
                }
            }
            cVar.r.e();
        }
    }

    static /* synthetic */ void b(c cVar) {
        if (!cVar.e) {
            if (cVar.a.isEmpty()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            cVar.g = true;
            cVar.b.a(cVar.c, null);
            for (e eVar : cVar.a) {
                if (!cVar.b(eVar)) {
                    eVar.a(cVar.q);
                }
            }
        }
    }
}
