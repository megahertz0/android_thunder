package com.xunlei.downloadprovider.member.register.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;

// compiled from: MobileSetupActivity.java
final class p implements OnFocusChangeListener {
    final /* synthetic */ MobileSetupActivity a;

    p(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        this.a.h.setImageDrawable(this.a.getResources().getDrawable(z ? 2130838543 : 2130838540));
    }
}
