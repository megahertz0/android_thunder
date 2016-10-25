package com.tencent.stat;

import java.util.List;

class p implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ int b;
    final /* synthetic */ n c;

    p(n nVar, List list, int i) {
        this.c = nVar;
        this.a = list;
        this.b = i;
    }

    public void run() {
        n.a(this.c, this.a, this.b);
    }
}
