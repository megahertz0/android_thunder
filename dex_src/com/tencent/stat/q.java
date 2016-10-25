package com.tencent.stat;

import java.util.List;

class q implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ n b;

    q(n nVar, List list) {
        this.b = nVar;
        this.a = list;
    }

    public void run() {
        n.a(this.b, this.a);
    }
}
