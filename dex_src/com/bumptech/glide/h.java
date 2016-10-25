package com.bumptech.glide;

// compiled from: RequestManager.java
final class h implements Runnable {
    final /* synthetic */ com.bumptech.glide.manager.h a;
    final /* synthetic */ g b;

    h(g gVar, com.bumptech.glide.manager.h hVar) {
        this.b = gVar;
        this.a = hVar;
    }

    public final void run() {
        this.a.a(this.b);
    }
}
