package com.xunlei.tdlive.play.view;

import android.view.View;

// compiled from: NormalScreenLayout.java
class q implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ NormalScreenLayout b;

    q(NormalScreenLayout normalScreenLayout, View view) {
        this.b = normalScreenLayout;
        this.a = view;
    }

    public void run() {
        this.a.setVisibility(0);
    }
}
