package com.xiaomi.smack;

class h extends Thread {
    final /* synthetic */ g a;

    h(g gVar, String str) {
        this.a = gVar;
        super(str);
    }

    public void run() {
        g.a(this.a);
    }
}
