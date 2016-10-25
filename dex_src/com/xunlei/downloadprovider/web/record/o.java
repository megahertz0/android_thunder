package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: FavorAndHistroyActivity.java
final class o implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    o(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        if (this.a.h.getTabType().equals("favor")) {
            LoginHelper.a();
            if (LoginHelper.c()) {
                this.a.t.setVisibility(0);
            }
        }
        this.a.a(false);
    }
}
