package com.baidu.mobads;

import android.view.ViewTreeObserver.OnPreDrawListener;

class r implements OnPreDrawListener {
    final /* synthetic */ AppActivity a;

    r(AppActivity appActivity) {
        this.a = appActivity;
    }

    public boolean onPreDraw() {
        this.a.mBottomView.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.runBottomViewEnterAnimation(this.a.C, this.a.mBottomView);
        return true;
    }
}
