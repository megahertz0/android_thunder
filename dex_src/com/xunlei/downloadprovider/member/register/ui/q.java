package com.xunlei.downloadprovider.member.register.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: MobileSetupActivity.java
final class q implements OnFocusChangeListener {
    final /* synthetic */ MobileSetupActivity a;

    q(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        int i = XZBDevice.Wait;
        if (z) {
            View f = this.a.l;
            if (this.a.f.length() > 0) {
                i = 0;
            }
            f.setVisibility(i);
            this.a.i.setImageDrawable(this.a.getResources().getDrawable(2130838560));
            return;
        }
        this.a.l.setVisibility(XZBDevice.Wait);
        this.a.i.setImageDrawable(this.a.getResources().getDrawable(2130838559));
    }
}
