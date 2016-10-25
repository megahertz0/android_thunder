package com.xunlei.downloadprovider.web.sniff.widget;

// compiled from: AnimProgressBar.java
final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        this.a.c.setRealProgress(this.a.a);
    }
}
