package com.tencent.stat;

import java.util.List;

class u implements c {
    final /* synthetic */ List a;
    final /* synthetic */ int b;
    final /* synthetic */ n c;

    u(n nVar, List list, int i) {
        this.c = nVar;
        this.a = list;
        this.b = i;
    }

    public void a() {
        this.c.a(this.a);
    }

    public void b() {
        this.c.a(this.a, 1);
        n nVar = this.c;
        nVar.b += this.b;
    }
}
