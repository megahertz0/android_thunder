package com.bumptech.glide.load.engine;

import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import com.bumptech.glide.h.h;
import com.bumptech.glide.load.engine.b.a;
import com.bumptech.glide.load.engine.b.e;
import com.bumptech.glide.load.engine.b.i;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

// compiled from: Engine.java
public final class b implements com.bumptech.glide.load.engine.b.i.a, d, a {
    public final Map<com.bumptech.glide.load.b, c> a;
    public final i b;
    public final a c;
    public final Map<com.bumptech.glide.load.b, WeakReference<g<?>>> d;
    public final b e;
    private final f f;
    private final k g;
    private ReferenceQueue<g<?>> h;

    // compiled from: Engine.java
    static class a {
        public final ExecutorService a;
        public final ExecutorService b;
        public final d c;

        public a(ExecutorService executorService, ExecutorService executorService2, d dVar) {
            this.a = executorService;
            this.b = executorService2;
            this.c = dVar;
        }
    }

    // compiled from: Engine.java
    private static class b implements a {
        private final com.bumptech.glide.load.engine.b.a.a a;
        private volatile a b;

        public b(com.bumptech.glide.load.engine.b.a.a aVar) {
            this.a = aVar;
        }

        public final a a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        this.b = this.a.a();
                    }
                    if (this.b == null) {
                        this.b = new com.bumptech.glide.load.engine.b.b();
                    }
                }
            }
            return this.b;
        }
    }

    // compiled from: Engine.java
    public static class c {
        public final c a;
        public final com.bumptech.glide.f.e b;

        public c(com.bumptech.glide.f.e eVar, c cVar) {
            this.b = eVar;
            this.a = cVar;
        }
    }

    // compiled from: Engine.java
    private static class d implements IdleHandler {
        private final Map<com.bumptech.glide.load.b, WeakReference<g<?>>> a;
        private final ReferenceQueue<g<?>> b;

        public d(Map<com.bumptech.glide.load.b, WeakReference<g<?>>> map, ReferenceQueue<g<?>> referenceQueue) {
            this.a = map;
            this.b = referenceQueue;
        }

        public final boolean queueIdle() {
            e eVar = (e) this.b.poll();
            if (eVar != null) {
                this.a.remove(e.a(eVar));
            }
            return true;
        }
    }

    // compiled from: Engine.java
    private static class e extends WeakReference<g<?>> {
        private final com.bumptech.glide.load.b a;

        public e(com.bumptech.glide.load.b bVar, g<?> gVar, ReferenceQueue<? super g<?>> referenceQueue) {
            super(gVar, referenceQueue);
            this.a = bVar;
        }
    }

    public b(i iVar, com.bumptech.glide.load.engine.b.a.a aVar, ExecutorService executorService, ExecutorService executorService2) {
        this(iVar, aVar, executorService, executorService2, (byte) 0);
    }

    private b(i iVar, com.bumptech.glide.load.engine.b.a.a aVar, ExecutorService executorService, ExecutorService executorService2, byte b) {
        this.b = iVar;
        this.e = new b(aVar);
        this.d = new HashMap();
        this.f = new f();
        this.a = new HashMap();
        this.c = new a(executorService, executorService2, this);
        this.g = new k();
        iVar.a((com.bumptech.glide.load.engine.b.i.a) this);
    }

    public static void a(String str, long j, com.bumptech.glide.load.b bVar) {
        new StringBuilder().append(str).append(" in ").append(com.bumptech.glide.h.d.a(j)).append("ms, key: ").append(bVar);
    }

    public final void a(com.bumptech.glide.load.b bVar, g<?> gVar) {
        h.a();
        if (gVar != null) {
            gVar.c = bVar;
            gVar.b = this;
            if (gVar.a) {
                this.d.put(bVar, new e(bVar, gVar, a()));
            }
        }
        this.a.remove(bVar);
    }

    public final void a(c cVar, com.bumptech.glide.load.b bVar) {
        h.a();
        if (cVar.equals((c) this.a.get(bVar))) {
            this.a.remove(bVar);
        }
    }

    public final void a(j<?> jVar) {
        h.a();
        this.g.a(jVar);
    }

    public final void b(com.bumptech.glide.load.b bVar, g gVar) {
        h.a();
        this.d.remove(bVar);
        if (gVar.a) {
            this.b.a(bVar, gVar);
        } else {
            this.g.a(gVar);
        }
    }

    public final ReferenceQueue<g<?>> a() {
        if (this.h == null) {
            this.h = new ReferenceQueue();
            Looper.myQueue().addIdleHandler(new d(this.d, this.h));
        }
        return this.h;
    }
}
