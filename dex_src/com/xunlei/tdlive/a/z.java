package com.xunlei.tdlive.a;

import android.view.View;

// compiled from: RecordAdapter.java
class z implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ x b;

    z(x xVar, View view) {
        this.b = xVar;
        this.a = view;
    }

    public void run() {
        this.b.a(this.a, 4000);
    }
}
