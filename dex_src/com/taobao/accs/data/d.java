package com.taobao.accs.data;

// compiled from: Taobao
class d implements Runnable {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public void run() {
        if (this.a.c != null) {
            this.a.c.a();
        }
    }
}
