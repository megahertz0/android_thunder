package com.xunlei.tdlive;

// compiled from: LiveListFragment.java
class u implements Runnable {
    final /* synthetic */ q a;

    u(q qVar) {
        this.a = qVar;
    }

    public void run() {
        q.b(this.a).m();
        q.a(this.a, "down_refresh");
    }
}
