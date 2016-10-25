package com.xunlei.downloadprovider.vod;

// compiled from: TouchListenerProxy.java
final class g implements Runnable {
    final /* synthetic */ TouchListenerProxy a;

    g(TouchListenerProxy touchListenerProxy) {
        this.a = touchListenerProxy;
    }

    public final void run() {
        if (TouchListenerProxy.a(this.a) != null && (this.a.a & 1) != 0) {
            TouchListenerProxy.b(this.a);
            TouchListenerProxy.a(this.a).onTouchSingleTap();
        }
    }
}
