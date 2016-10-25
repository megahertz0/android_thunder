package com.baidu.mobads;

import android.view.ViewTreeObserver.OnPreDrawListener;

class p implements OnPreDrawListener {
    final /* synthetic */ AppActivity a;

    p(AppActivity appActivity) {
        this.a = appActivity;
    }

    public boolean onPreDraw() {
        this.a.d.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.a(this.a.d);
        return true;
    }
}
