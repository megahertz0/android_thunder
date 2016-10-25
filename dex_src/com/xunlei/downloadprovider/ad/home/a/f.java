package com.xunlei.downloadprovider.ad.home.a;

// compiled from: LoadADClient.java
final class f implements Runnable {
    final /* synthetic */ c a;

    f(c cVar) {
        this.a = cVar;
    }

    public final void run() {
        c.c(this.a).notifyDataSetChanged();
    }
}
