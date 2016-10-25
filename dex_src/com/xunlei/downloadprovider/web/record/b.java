package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: FavorAndHistroyActivity.java
final class b implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    b(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        if (!this.a.i && !this.a.h.getTabType().equals("\u6536\u85cf")) {
            this.a.p.a(0);
            this.a.f();
        }
    }
}
