package com.taobao.accs.net;

// compiled from: Taobao
class n implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ l b;

    n(l lVar, String str) {
        this.b = lVar;
        this.a = str;
    }

    public void run() {
        if (this.a != null && this.a.equals(l.c(this.b)) && l.b(this.b) == 2) {
            l.a(this.b, false);
            l.b(this.b, true);
            this.b.i();
            l.d(this.b).setCloseReason("conn timeout");
        }
    }
}
