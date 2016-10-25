package com.xunlei.downloadprovidershare;

// compiled from: ShareHelper.java
final class ac implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ab b;

    ac(ab abVar, String str) {
        this.b = abVar;
        this.a = str;
    }

    public final void run() {
        this.b.a.b.a.c.a = this.a;
        if (d.c(this.b.a.b.a.d) != null) {
            d.c(this.b.a.b.a.d).onShareTargetClicked(null, this.b.a.b.a.c);
        }
        d.a(this.b.a.b.a.d);
    }
}
