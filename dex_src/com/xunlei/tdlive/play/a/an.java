package com.xunlei.tdlive.play.a;

import android.view.MotionEvent;

// compiled from: ReplayDialogPresenter.java
class an implements Runnable {
    final /* synthetic */ MotionEvent a;
    final /* synthetic */ aa$a b;

    an(aa$a com_xunlei_tdlive_play_a_aa_a, MotionEvent motionEvent) {
        this.b = com_xunlei_tdlive_play_a_aa_a;
        this.a = motionEvent;
    }

    public void run() {
        super.onSingleTapUp(this.a);
    }
}
