package com.xunlei.tdlive.play.a;

import android.view.MotionEvent;

// compiled from: ReplayDialogPresenter.java
private class aa$a extends aa$e {
    final /* synthetic */ aa a;

    private aa$a(aa aaVar) {
        this.a = aaVar;
        super(aaVar);
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        aa.k(this.a).m().getSingleTagUpHandler().postDelayed(new an(this, motionEvent), 100);
        return true;
    }
}
