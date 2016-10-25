package com.xunlei.downloadprovider.discovery;

// compiled from: DiscoveryFragment.java
final class e implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ d c;

    e(d dVar, int i, int i2) {
        this.c = dVar;
        this.a = i;
        this.b = i2;
    }

    public final void run() {
        DiscoveryFragment.a(this.c.a, this.a, this.b);
    }
}
