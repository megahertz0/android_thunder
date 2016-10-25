package com.tencent.wxop.stat;

import java.util.List;

class ba implements h {
    final /* synthetic */ List a;
    final /* synthetic */ boolean b;
    final /* synthetic */ au c;

    ba(au auVar, List list, boolean z) {
        this.c = auVar;
        this.a = list;
        this.b = z;
    }

    public void a() {
        StatServiceImpl.c();
        this.c.a(this.a, this.b, true);
    }

    public void b() {
        StatServiceImpl.d();
        this.c.a(this.a, 1, this.b, true);
    }
}
