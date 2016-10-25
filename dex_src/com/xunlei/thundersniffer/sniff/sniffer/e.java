package com.xunlei.thundersniffer.sniff.sniffer;

import java.util.List;

final class e implements SnifferSvrGetOperation$SnifferSvrGetListener {
    final /* synthetic */ Sniffer a;

    e(Sniffer sniffer) {
        this.a = sniffer;
    }

    public final void onSnifferSvrGetFinish(String str, int i, List<String> list, List<at> list2, List<at> list3, SnifferSvrGetOperation$a snifferSvrGetOperation$a) {
        synchronized (this.a) {
            if (this.a.e != null) {
                this.a.e.b("Sniffer.Sniffer", "End SnifferGet");
            }
            if (this.a.i) {
                return;
            }
            int i2;
            if (i == 1) {
                list.clear();
            }
            Sniffer sniffer = this.a;
            if (snifferSvrGetOperation$a == null) {
                i2 = -1;
            } else {
                i2 = snifferSvrGetOperation$a.e;
            }
            at atVar;
            if (sniffer.a(str, list, i2)) {
                if (!(list2 == null || list2.isEmpty())) {
                    for (at atVar2 : list2) {
                        this.a.v.a(atVar2);
                    }
                }
                this.a.k.a();
            } else if (list2 == null || list2.isEmpty()) {
                if (list3 == null || list3.isEmpty()) {
                    atVar2 = new at();
                    atVar2.e = str;
                    atVar2.a = 0;
                    atVar2.d = this.a.r.g(str);
                    if (i == 1) {
                        atVar2.b = 3;
                    }
                    this.a.v.a(atVar2);
                }
                this.a.v.c();
            } else {
                for (at atVar22 : list2) {
                    this.a.v.a(atVar22);
                }
                this.a.v.c();
            }
        }
    }

    public final boolean onSnifferSvrGetResult(String str, at atVar) {
        this.a.v.a(atVar);
        return true;
    }
}
