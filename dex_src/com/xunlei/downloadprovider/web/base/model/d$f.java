package com.xunlei.downloadprovider.web.base.model;

// compiled from: ShortMovieDetailDataLoader.java
private class d$f implements Runnable {
    public long a;
    final /* synthetic */ d b;

    private d$f(d dVar) {
        this.b = dVar;
        this.a = -1;
    }

    public final void run() {
        if (this.b.b != null) {
            this.b.h = this.b.b.a(this.a);
        }
    }
}
