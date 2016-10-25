package com.nostra13.universalimageloader.a.a.a.a;

import java.util.concurrent.Callable;

// compiled from: DiskLruCache.java
final class b implements Callable<Void> {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        return a();
    }

    private Void a() throws Exception {
        synchronized (this.a) {
            if (a.a(this.a) == null) {
            } else {
                a.b(this.a);
                a.c(this.a);
                if (a.d(this.a)) {
                    a.e(this.a);
                    a.f(this.a);
                }
            }
        }
        return null;
    }
}
