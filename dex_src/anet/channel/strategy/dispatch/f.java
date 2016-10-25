package anet.channel.strategy.dispatch;

import anet.channel.c.c;
import java.util.Map;

// compiled from: Taobao
class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void run() {
        synchronized (this.a) {
            Map map = this.a.a;
            this.a.a = null;
            c.a(new a(map), 0);
        }
    }
}
