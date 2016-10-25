package com.tencent.wxop.stat;

import com.tencent.wxop.stat.a.e;

class ay implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ h b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;
    final /* synthetic */ au e;

    ay(au auVar, e eVar, h hVar, boolean z, boolean z2) {
        this.e = auVar;
        this.a = eVar;
        this.b = hVar;
        this.c = z;
        this.d = z2;
    }

    public void run() {
        au.a(this.e, this.a, this.b, this.c, this.d);
    }
}
