package com.xunlei.downloadprovider.web.base.model;

import com.android.volley.f;
import com.android.volley.toolbox.o;
import com.xunlei.downloadprovider.c.a.e;
import com.xunlei.downloadprovider.c.a.l;
import com.xunlei.downloadprovider.c.a.m;
import com.xunlei.downloadprovider.c.c;
import com.xunlei.downloadprovider.j.a;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: ShortMovieDetailDataLoader.java
private class d$e implements Runnable {
    final /* synthetic */ d a;

    private d$e(d dVar) {
        this.a = dVar;
    }

    public final void run() {
        if (this.a.b != null && this.a.a != null) {
            ArrayList a = this.a.b.a();
            if (a != null && a.size() != 0) {
                this.a.a.b = a.b();
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    a.a aVar = (a.a) it.next();
                    e eVar = new e();
                    eVar.a = 1;
                    eVar.b = aVar.e;
                    eVar.c = aVar.f;
                    this.a.a.a(eVar);
                    com.xunlei.downloadprovider.c.a aVar2 = this.a.a;
                    long j = aVar.a;
                    boolean z = aVar.c;
                    a sVar = new s(this);
                    if (aVar2.c == null) {
                        throw new IllegalStateException("no comment resource attached");
                    }
                    l mVar = new m();
                    mVar.c = aVar2.c.c;
                    mVar.a = aVar2.c.b;
                    mVar.b = aVar2.c.a;
                    mVar.f = z;
                    mVar.d = j;
                    o a2 = mVar.a(new com.xunlei.downloadprovider.c.o(aVar2, sVar, j), new c(aVar2, sVar));
                    a2.setRetryPolicy(new f(10000, 1, 1.0f));
                    a2.setTag("SYNC");
                    aVar2.b.a(a2);
                }
            }
        }
    }
}
