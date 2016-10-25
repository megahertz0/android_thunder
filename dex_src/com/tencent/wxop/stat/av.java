package com.tencent.wxop.stat;

import java.util.List;

class av implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ int b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;
    final /* synthetic */ au e;

    av(au auVar, List list, int i, boolean z, boolean z2) {
        this.e = auVar;
        this.a = list;
        this.b = i;
        this.c = z;
        this.d = z2;
    }

    public void run() {
        au.a(this.e, this.a, this.b, this.c);
        if (this.d) {
            this.a.clear();
        }
    }
}
