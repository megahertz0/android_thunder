package com.xunlei.downloadprovider.service.downloads.task.b;

import com.xunlei.downloadprovider.service.downloads.task.b.a.b;
import com.xunlei.downloadprovider.service.downloads.task.info.c;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// compiled from: TaskExtraInfoController.java
public class d {
    private static d b;
    public final HashMap<Long, c> a;
    private ExecutorService c;

    public static d a() {
        if (b == null) {
            synchronized (d.class) {
                b = new d();
            }
        }
        return b;
    }

    private d() {
        this.c = Executors.newSingleThreadExecutor();
        this.a = new HashMap();
    }

    public final c a(long j) {
        if (j == -1) {
            return null;
        }
        synchronized (this.a) {
            if (this.a.containsKey(Long.valueOf(j))) {
                c cVar = (c) this.a.get(Long.valueOf(j));
                return cVar;
            }
            cVar = b.a().a(j);
            synchronized (this.a) {
                this.a.put(Long.valueOf(j), cVar);
            }
            return cVar;
        }
    }

    public final void a(c cVar) {
        if (cVar != null) {
            synchronized (this.a) {
                if (this.a.containsKey(Long.valueOf(cVar.a))) {
                    c cVar2 = (c) this.a.get(Long.valueOf(cVar.a));
                    if (cVar2 == null) {
                        this.a.put(Long.valueOf(cVar.a), cVar);
                    } else if (!(cVar2 == cVar || cVar == null)) {
                        if (cVar instanceof c) {
                            c cVar3 = cVar;
                            cVar2.a = cVar3.a;
                            cVar2.b = cVar3.b;
                            cVar2.c = cVar3.c;
                            cVar2.d = cVar3.d;
                            cVar2.e = cVar3.e;
                            cVar2.f = cVar3.f;
                            cVar2.g = cVar3.g;
                            cVar2.h = cVar3.h;
                            cVar2.i = cVar3.i;
                            cVar2.j = cVar3.j;
                            cVar2.k = cVar3.k;
                            cVar2.l = cVar3.l;
                            cVar2.m = cVar3.m;
                            cVar2.n = cVar3.n;
                            cVar2.o = cVar3.o;
                        }
                    }
                } else {
                    this.a.put(Long.valueOf(cVar.a), cVar);
                }
            }
            b.a().a(cVar);
        }
    }

    public static void a(long j, long j2) {
        b.a().a(j, j2, true);
    }

    public static boolean b(long j) {
        return b.a().c(j);
    }

    public final void c(long j) {
        this.c.execute(new e(this, j));
    }
}
