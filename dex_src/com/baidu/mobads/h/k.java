package com.baidu.mobads.h;

class k implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ g b;

    k(g gVar, boolean z) {
        this.b = gVar;
        this.a = z;
    }

    public void run() {
        new Thread(new l(this)).start();
    }
}
