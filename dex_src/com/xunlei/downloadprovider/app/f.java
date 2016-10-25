package com.xunlei.downloadprovider.app;

import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.service.downloads.kernel.c;
import com.xunlei.downloadprovider.service.downloads.kernel.e;

// compiled from: BrothersApplication.java
final class f implements Runnable {
    final /* synthetic */ BrothersApplication a;

    f(BrothersApplication brothersApplication) {
        this.a = brothersApplication;
    }

    public final void run() {
        c.a().b(this.a);
        BrothersApplication.o;
        c a = c.a();
        if (!(a.a == null || a.b == null)) {
            long[] a2 = c.a(new e(a.b, a.a).a());
            if (a2.length > 0) {
                a.a(a2);
            }
        }
        g.a().f = g.d();
    }
}
