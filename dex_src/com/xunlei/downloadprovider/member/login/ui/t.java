package com.xunlei.downloadprovider.member.login.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LoginActivity.java
final class t implements OnFocusChangeListener {
    final /* synthetic */ LoginActivity a;

    t(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        int i = XZBDevice.Wait;
        if (z) {
            View e = this.a.h;
            if (this.a.e.length() > 0) {
                i = 0;
            }
            e.setVisibility(i);
            this.a.q.setImageDrawable(this.a.getResources().getDrawable(2130838532));
            return;
        }
        this.a.h.setVisibility(XZBDevice.Wait);
        this.a.q.setImageDrawable(this.a.getResources().getDrawable(2130838531));
    }
}
