package com.xunlei.downloadprovider.member.login.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;

// compiled from: LoginActivity.java
final class e implements OnFocusChangeListener {
    final /* synthetic */ LoginActivity a;

    e(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        this.a.s.setImageDrawable(this.a.getResources().getDrawable(z ? 2130838539 : 2130838538));
    }
}
