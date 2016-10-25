package com.xunlei.downloadprovider.member.register.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MobileSetupActivity.java
final class d implements OnClickListener {
    final /* synthetic */ MobileSetupActivity a;

    d(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void onClick(View view) {
        MobileSetupActivity.b(this.a, view);
        this.a.onBackPressed();
    }
}
