package com.xunlei.downloadprovider.web.base;

// compiled from: KandanListActivity.java
final class m implements Runnable {
    final /* synthetic */ KandanListActivity a;

    m(KandanListActivity kandanListActivity) {
        this.a = kandanListActivity;
    }

    public final void run() {
        if (this.a.r) {
            if (this.a.x < this.a.w) {
                KandanListActivity.l(this.a);
                this.a.o.setMaxLines(this.a.x);
                this.a.b();
                this.a.u.postDelayed(this, 10);
            }
        } else if (this.a.x > 2) {
            KandanListActivity.p(this.a);
            this.a.o.setMaxLines(this.a.x);
            this.a.b();
            this.a.u.postDelayed(this, 10);
        }
    }
}
