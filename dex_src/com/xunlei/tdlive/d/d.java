package com.xunlei.tdlive.d;

// compiled from: MDPlayer.java
class d implements Runnable {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void run() {
        this.a.a(null);
        if (c.a(this.a) == 1) {
            c.a(this.a, 0);
            if (c.b(this.a) != null) {
                c.b(this.a).a();
            }
        }
    }
}
