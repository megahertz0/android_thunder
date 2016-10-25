package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: FavorAndHistroyActivity.java
final class k implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    k(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        LoginHelper.a().a(this.a, null, 1);
    }
}
