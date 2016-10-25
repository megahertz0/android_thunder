package com.xunlei.downloadprovider.member.login.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LoginActivity.java
final class v implements OnFocusChangeListener {
    final /* synthetic */ LoginActivity a;

    v(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        int i = XZBDevice.Wait;
        if (z) {
            this.a.f.setCompoundDrawables(null, null, null, null);
            this.a.g.setVisibility(this.a.f.length() > 0 ? 0 : 8);
            TextView h = this.a.i;
            if (this.a.f.length() <= 0) {
                i = 0;
            }
            h.setVisibility(i);
            this.a.r.setImageDrawable(this.a.getResources().getDrawable(2130838551));
            this.a.q.setImageDrawable(this.a.getResources().getDrawable(2130838531));
            return;
        }
        this.a.f.setCompoundDrawables(null, null, null, null);
        this.a.g.setVisibility(XZBDevice.Wait);
        this.a.r.setImageDrawable(this.a.getResources().getDrawable(2130838550));
    }
}
