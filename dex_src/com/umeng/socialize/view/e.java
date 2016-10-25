package com.umeng.socialize.view;

import android.view.View;

// compiled from: OauthDialog.java
class e implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ c c;

    e(c cVar, View view, View view2) {
        this.c = cVar;
        this.a = view;
        this.b = view2;
    }

    public void run() {
        this.a.setVisibility(0);
        if (!(this.b.getVisibility() == 0 || this.c.d.m == null || this.c.d.m.size() <= 0)) {
            this.b.setVisibility(0);
        }
        this.c.requestLayout();
    }
}
