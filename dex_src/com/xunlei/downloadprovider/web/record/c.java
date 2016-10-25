package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: FavorAndHistroyActivity.java
final class c implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    c(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        if (!this.a.i && !this.a.h.getTabType().equals("\u6d4f\u89c8\u5386\u53f2")) {
            this.a.p.a(1);
            this.a.f();
        }
    }
}
