package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: FavorAndHistroyActivity.java
final class f implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    f(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        if (this.a.y != null) {
            this.a.y.show();
        }
    }
}
