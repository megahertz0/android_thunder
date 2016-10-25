package com.xunlei.downloadprovider.i.a;

// compiled from: Update.java
final class k implements Runnable {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public final void run() {
        try {
            if (!(c.l(this.a.a) || this.a.a.b == null || c.e(this.a.a).isFinishing())) {
                this.a.a.b.cancel();
            }
        } catch (Exception e) {
        }
        if (!c.l(this.a.a)) {
            c.B(this.a.a);
        } else if (!c.b(this.a.a)) {
            if (c.e(this.a.a) != null && !c.e(this.a.a).isFinishing()) {
                if (c.a) {
                    c.C(this.a.a);
                    return;
                } else {
                    c.h(this.a.a);
                    return;
                }
            }
            return;
        }
        c.t(this.a.a);
    }
}
