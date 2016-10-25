package com.tencent.wxop.stat;

class bb implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ au b;

    bb(au auVar, int i) {
        this.b = auVar;
        this.a = i;
    }

    public void run() {
        au.a(this.b, this.a, true);
        au.a(this.b, this.a, false);
    }
}
