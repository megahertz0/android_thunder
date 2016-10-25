package com.tencent.wxop.stat;

import java.util.List;

class aw implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ boolean b;
    final /* synthetic */ boolean c;
    final /* synthetic */ au d;

    aw(au auVar, List list, boolean z, boolean z2) {
        this.d = auVar;
        this.a = list;
        this.b = z;
        this.c = z2;
    }

    public void run() {
        au.a(this.d, this.a, this.b);
        if (this.c) {
            this.a.clear();
        }
    }
}
