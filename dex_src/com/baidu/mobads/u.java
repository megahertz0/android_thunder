package com.baidu.mobads;

import android.view.View;

class u implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ AppActivity c;

    u(AppActivity appActivity, View view, View view2) {
        this.c = appActivity;
        this.a = view;
        this.b = view2;
    }

    public void run() {
        this.c.b(this.a);
        this.c.b(this.b);
    }
}
