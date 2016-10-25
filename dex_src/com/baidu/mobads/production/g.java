package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.download.activate.IXMonitorActivation;

class g implements Runnable {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void run() {
        try {
            if (a.a != null) {
                IXMonitorActivation xMonitorActivation = a.a.getXMonitorActivation(this.a.a, this.a.b.s);
                xMonitorActivation.setIXActivateListener(new h(this));
                xMonitorActivation.startMonitor();
            }
        } catch (Throwable e) {
            this.a.b.s.e(e);
        }
    }
}
