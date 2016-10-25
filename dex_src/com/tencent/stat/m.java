package com.tencent.stat;

class m implements c {
    final /* synthetic */ k a;

    m(k kVar) {
        this.a = kVar;
    }

    public void a() {
        if (n.b().a() >= StatConfig.getMaxBatchReportCount()) {
            n.b().a(StatConfig.getMaxBatchReportCount());
        }
    }

    public void b() {
    }
}
