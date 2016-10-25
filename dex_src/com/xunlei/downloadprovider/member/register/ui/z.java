package com.xunlei.downloadprovider.member.register.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;

// compiled from: RegisterSuccessActivity.java
final class z implements OnFocusChangeListener {
    final /* synthetic */ RegisterSuccessActivity a;

    z(RegisterSuccessActivity registerSuccessActivity) {
        this.a = registerSuccessActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        this.a.f.setImageDrawable(this.a.getResources().getDrawable(z ? 2130838549 : 2130838548));
    }
}
