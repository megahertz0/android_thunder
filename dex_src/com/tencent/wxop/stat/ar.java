package com.tencent.wxop.stat;

class ar implements h {
    final /* synthetic */ aq a;

    ar(aq aqVar) {
        this.a = aqVar;
    }

    public void a() {
        StatServiceImpl.c();
        if (au.b().a() >= StatConfig.getMaxBatchReportCount()) {
            au.b().a(StatConfig.getMaxBatchReportCount());
        }
    }

    public void b() {
        StatServiceImpl.d();
    }
}
