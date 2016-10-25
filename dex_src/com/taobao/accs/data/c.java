package com.taobao.accs.data;

import com.taobao.accs.ut.monitor.TrafficsMonitor.a;

// compiled from: Taobao
class c implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ b b;

    c(b bVar, a aVar) {
        this.b = bVar;
        this.a = aVar;
    }

    public void run() {
        if (this.b.c != null) {
            this.b.c.a(this.a);
        }
    }
}
