package com.xunlei.downloadprovider.player.wrapper;

// compiled from: AsyncMediaPlayer.java
final class c implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ a b;

    c(a aVar, int i) {
        this.b = aVar;
        this.a = i;
    }

    public final void run() {
        this.b.f.a(this.a);
    }
}
