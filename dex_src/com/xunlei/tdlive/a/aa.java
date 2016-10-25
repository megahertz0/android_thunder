package com.xunlei.tdlive.a;

import android.view.View;

// compiled from: RecordAdapter.java
class aa implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ x b;

    aa(x xVar, View view) {
        this.b = xVar;
        this.a = view;
    }

    public void run() {
        x.a(this.b, this.a, 4000);
    }
}
