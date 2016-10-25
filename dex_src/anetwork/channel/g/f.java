package anetwork.channel.g;

import anet.channel.util.ALog;
import anetwork.channel.a.b;
import anetwork.channel.a.c;

// compiled from: Taobao
final class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void run() {
        if (this.a.b.i.g != null) {
            long currentTimeMillis = System.currentTimeMillis();
            c cVar = this.a.b.i.g;
            b bVar = this.a.b.g;
            if (cVar.a != null) {
                bVar.a.toByteArray();
            }
            ALog.i("ANet.UnifiedNetworkTask", "write image cache", this.a.b.i.c, "cost", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
    }
}
