package com.baidu.mobads;

import android.view.View;
import android.view.View.OnClickListener;

class s implements OnClickListener {
    final /* synthetic */ AppActivity a;

    s(AppActivity appActivity) {
        this.a = appActivity;
    }

    public void onClick(View view) {
        this.a.runBottomViewExitAnimation(this.a.C, this.a.mBottomView);
    }
}
