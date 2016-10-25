package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.xunlei.thundersniffer.sniff.sniffer.Sniffer.b;
import com.xunlei.thundersniffer.sniff.sniffer.Sniffer.d;

final class g implements Runnable {
    final /* synthetic */ at a;
    final /* synthetic */ b b;

    g(b bVar, at atVar) {
        this.b = bVar;
        this.a = atVar;
    }

    public final void run() {
        if (this.a.k == 1) {
            if (this.b.f.c.a.getXunleiVodplayEnabled()) {
                d.a(this.a.i, this.a.t).start();
            }
            String a = this.b.f.r.a(this.a.e);
            if (!TextUtils.isEmpty(a)) {
                new StringBuilder("[Title] frUrl: ").append(a).append(" frOld: ").append(this.a.n);
                this.a.n = aj.a(this.a.n, a);
            }
            if (this.a.i != null && this.a.i.size() > 1 && this.b.f.c.a.getResourceSortEnabled()) {
                new StringBuilder("Sorting: ").append(this.a.i.size());
                this.b.f.e.b("Sniffer.Sniffer", "Sorting Start");
                ar.a(this.a.i);
                this.b.f.e.b("Sniffer.Sniffer", new StringBuilder("Sorting End - count: ").append(this.a.i.size()).toString());
            }
        }
        synchronized (this.b.b) {
            this.b.b.remove(this.a);
            this.b.c.add(this.a);
        }
        this.b.b(this.a);
        this.b.a();
    }
}
