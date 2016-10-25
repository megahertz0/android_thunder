package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: RequestQueue.java
public final class p {
    final Map<String, Queue<Request<?>>> a;
    final Set<Request<?>> b;
    final PriorityBlockingQueue<Request<?>> c;
    List<Object> d;
    private AtomicInteger e;
    private final PriorityBlockingQueue<Request<?>> f;
    private final b g;
    private final i h;
    private final s i;
    private j[] j;
    private c k;

    // compiled from: RequestQueue.java
    public static interface a {
        boolean a(Request<?> request);
    }

    public p(b bVar, i iVar, int i, s sVar) {
        this.e = new AtomicInteger();
        this.a = new HashMap();
        this.b = new HashSet();
        this.c = new PriorityBlockingQueue();
        this.f = new PriorityBlockingQueue();
        this.d = new ArrayList();
        this.g = bVar;
        this.h = iVar;
        this.j = new j[i];
        this.i = sVar;
    }

    private p(b bVar, i iVar) {
        this(bVar, iVar, 4, new g(new Handler(Looper.getMainLooper())));
    }

    public p(b bVar, i iVar, byte b) {
        this(bVar, iVar);
    }

    public final void a() {
        int i = 0;
        if (this.k != null) {
            c cVar = this.k;
            cVar.a = true;
            cVar.interrupt();
        }
        for (int i2 = 0; i2 < this.j.length; i2++) {
            if (this.j[i2] != null) {
                j jVar = this.j[i2];
                jVar.a = true;
                jVar.interrupt();
            }
        }
        this.k = new c(this.c, this.f, this.g, this.i);
        this.k.start();
        while (i < this.j.length) {
            j jVar2 = new j(this.f, this.h, this.g, this.i);
            this.j[i] = jVar2;
            jVar2.start();
            i++;
        }
    }

    private void a(a aVar) {
        synchronized (this.b) {
            for (Request request : this.b) {
                if (aVar.a(request)) {
                    request.cancel();
                }
            }
        }
    }

    public final void a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        a(new q(this, obj));
    }

    public final <T> Request<T> a(Request<T> request) {
        request.setRequestQueue(this);
        synchronized (this.b) {
            this.b.add(request);
        }
        request.setSequence(this.e.incrementAndGet());
        request.addMarker("add-to-queue");
        if (request.shouldCache()) {
            synchronized (this.a) {
                String cacheKey = request.getCacheKey();
                if (this.a.containsKey(cacheKey)) {
                    Queue queue = (Queue) this.a.get(cacheKey);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(request);
                    this.a.put(cacheKey, queue);
                    if (x.b) {
                        x.a("Request for cacheKey=%s is in flight, putting on hold.", cacheKey);
                    }
                } else {
                    this.a.put(cacheKey, null);
                    this.c.add(request);
                }
            }
        } else {
            this.f.add(request);
        }
        return request;
    }
}
