package com.tencent.wxop.stat;

class at implements h {
    final /* synthetic */ aq a;

    at(aq aqVar) {
        this.a = aqVar;
    }

    public void a() {
        StatServiceImpl.c();
        if (au.b().a > 0) {
            StatServiceImpl.commitEvents(this.a.d, -1);
        }
    }

    public void b() {
        au.b().a(this.a.a, null, this.a.c, true);
        StatServiceImpl.d();
    }
}
