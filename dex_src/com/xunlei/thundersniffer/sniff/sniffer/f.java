package com.xunlei.thundersniffer.sniff.sniffer;

final class f implements SnifferSvrCheckWordOperation$SnifferSvrCheckWordListener {
    final /* synthetic */ Sniffer a;

    f(Sniffer sniffer) {
        this.a = sniffer;
    }

    public final void onSnifferSvrCheckWorFinish(String str, boolean z, SnifferSvrCheckWordOperation$a snifferSvrCheckWordOperation$a) {
        synchronized (this.a) {
            if (this.a.e != null) {
                this.a.e.b("Sniffer.Sniffer", "End SnifferCheckWord");
            }
            if (this.a.i) {
                return;
            }
            if (z) {
                at atVar = new at();
                atVar.e = str;
                atVar.a = 0;
                atVar.d = this.a.r.g(str);
                atVar.b = 3;
                this.a.v.a(atVar);
                this.a.v.c();
            } else {
                Sniffer sniffer = this.a;
                if (!sniffer.a(str, snifferSvrCheckWordOperation$a.b, snifferSvrCheckWordOperation$a.d) || sniffer.k == null) {
                    at atVar2 = new at();
                    atVar2.e = str;
                    atVar2.a = 1;
                    atVar2.d = sniffer.r.g(str);
                    atVar2.b = 0;
                    sniffer.v.a(atVar2);
                    sniffer.v.c();
                } else {
                    sniffer.k.a();
                }
            }
        }
    }
}
