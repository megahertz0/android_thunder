package com.taobao.accs.net;

import anet.channel.appmonitor.AppMonitor;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.ut.monitor.SessionMonitor;
import com.taobao.accs.ut.monitor.TrafficsMonitor;

// compiled from: Taobao
class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        AppMonitor.getInstance().register(NetPerformanceMonitor.class);
        AppMonitor.getInstance().register(TrafficsMonitor.class);
        AppMonitor.getInstance().register(SessionMonitor.class);
    }
}
