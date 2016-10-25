package com.tencent.stat;

import java.util.List;

class f implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ c b;
    final /* synthetic */ d c;

    f(d dVar, List list, c cVar) {
        this.c = dVar;
        this.a = list;
        this.b = cVar;
    }

    public void run() {
        try {
            this.c.a(this.a, this.b);
        } catch (Object th) {
            d.c().e(th);
        }
    }
}
