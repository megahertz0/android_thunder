package com.xunlei.tdlive.control;

import android.view.View;

// compiled from: TabBar.java
class r implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ TabBar b;

    r(TabBar tabBar, View view) {
        this.b = tabBar;
        this.a = view;
    }

    public void run() {
        this.b.onClick(this.a);
    }
}
