package com.xunlei.tdlive.a;

// compiled from: GiftAdapter.java
class h implements Runnable {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void run() {
        try {
            this.a.b.d().writeToStream(c.a(this.a.b).openFileOutput(".gift.cache.dat", 0));
        } catch (Throwable th) {
        }
    }
}
