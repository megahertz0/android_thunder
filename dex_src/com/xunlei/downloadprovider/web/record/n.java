package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.d;

// compiled from: FavorAndHistroyActivity.java
final class n implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    n(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        if (!d.a(this.a.h.c)) {
            if (this.a.h.getTabType().equals("favor")) {
                this.a.t.setVisibility(XZBDevice.Wait);
            }
            this.a.a(true);
        }
    }
}
