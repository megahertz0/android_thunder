package com.tencent.stat;

import com.tencent.stat.a.e;

class r implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ c b;
    final /* synthetic */ n c;

    r(n nVar, e eVar, c cVar) {
        this.c = nVar;
        this.a = eVar;
        this.b = cVar;
    }

    public void run() {
        n.a(this.c, this.a, this.b);
    }
}
