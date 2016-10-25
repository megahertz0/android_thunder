package com.xunlei.tdlive;

import android.view.MotionEvent;

// compiled from: LivePlayerDialog.java
class ck implements Runnable {
    final /* synthetic */ MotionEvent a;
    final /* synthetic */ au$a b;

    ck(au$a com_xunlei_tdlive_au_a, MotionEvent motionEvent) {
        this.b = com_xunlei_tdlive_au_a;
        this.a = motionEvent;
    }

    public void run() {
        super.onSingleTapUp(this.a);
    }
}
