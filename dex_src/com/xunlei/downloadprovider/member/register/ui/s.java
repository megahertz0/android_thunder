package com.xunlei.downloadprovider.member.register.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;

// compiled from: MobileSetupActivity.java
final class s implements OnFocusChangeListener {
    final /* synthetic */ MobileSetupActivity a;

    s(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        this.a.j.setImageDrawable(this.a.getResources().getDrawable(z ? 2130838551 : 2130838550));
    }
}
