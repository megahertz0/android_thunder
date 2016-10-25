package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: FavorAndHistroyActivity.java
final class p implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    p(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        if (this.a.h.d()) {
            this.a.h.a(false);
            this.a.a(this.a.getResources().getString(2131230862));
        } else {
            this.a.h.a(true);
            this.a.a(this.a.getResources().getString(2131230861));
        }
        FavorAndHistroyActivity.b(this.a, this.a.h.d());
        this.a.d();
    }
}
