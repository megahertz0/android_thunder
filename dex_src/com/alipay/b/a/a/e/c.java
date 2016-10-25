package com.alipay.b.a.a.e;

final class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        try {
            this.a.b();
        } catch (Throwable e) {
            d.a(e);
        }
    }
}
