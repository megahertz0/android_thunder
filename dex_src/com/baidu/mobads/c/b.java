package com.baidu.mobads.c;

class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        new Thread(new c(this)).start();
    }
}
