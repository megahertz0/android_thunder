package com.xunlei.thundersniffer.sniff.sniffer;

final class d implements au$a {
    final /* synthetic */ Sniffer a;

    d(Sniffer sniffer) {
        this.a = sniffer;
    }

    public final void a(SniffingTask sniffingTask) {
        if (sniffingTask instanceof SniffingDetailPageTask) {
            this.a.v.a(((SniffingDetailPageTask) sniffingTask).c);
            SniffingDetailPageTask sniffingDetailPageTask = (SniffingDetailPageTask) sniffingTask;
            sniffingDetailPageTask.n = null;
            sniffingDetailPageTask.a();
            sniffingDetailPageTask.m = null;
        }
    }

    public final void a() {
        this.a.v.c();
        this.a.k = null;
    }
}
