package com.xunlei.downloadprovider.web.base.model;

// compiled from: ShortMovieDetailDataLoader.java
private class d$d implements Runnable {
    String a;
    final /* synthetic */ d b;

    private d$d(d dVar) {
        this.b = dVar;
    }

    public final void run() {
        if (this.b.b != null) {
            this.b.h = this.b.b.a(this.a);
        }
    }
}
