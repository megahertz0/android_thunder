package com.xunlei.downloadprovider.web.record;

import com.xunlei.downloadprovider.app.ui.ScrollLayout.c;

// compiled from: FavorAndHistroyActivity.java
final class d implements c {
    final /* synthetic */ FavorAndHistroyActivity a;

    d(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void a(int i) {
        if (i == 0) {
            this.a.h = this.a.f;
            this.a.n.setSelected(true);
            this.a.o.setSelected(false);
        } else {
            this.a.h = this.a.g;
            this.a.n.setSelected(false);
            this.a.o.setSelected(true);
        }
        this.a.e();
        this.a.b();
        this.a.f();
    }
}
