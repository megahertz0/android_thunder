package com.baidu.mobads.production;

class n implements Runnable {
    final /* synthetic */ a a;

    n(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.h.resize(this.a.getProdBase().getWidth(), this.a.getProdBase().getHeight());
    }
}
