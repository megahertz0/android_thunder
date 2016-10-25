package com.baidu.mobads.j;

class f implements Runnable {
    final /* synthetic */ Runnable a;
    final /* synthetic */ d b;

    f(d dVar, Runnable runnable) {
        this.b = dVar;
        this.a = runnable;
    }

    public void run() {
        this.a.run();
    }
}
