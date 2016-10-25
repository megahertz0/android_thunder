package com.xunlei.tdlive;

import android.view.MotionEvent;

// compiled from: LivePlayerDialog.java
private class au$a extends au$b {
    final /* synthetic */ au a;

    private au$a(au auVar) {
        this.a = auVar;
        super(auVar);
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        au.l(this.a).getSingleTagUpHandler().postDelayed(new ck(this, motionEvent), 100);
        return true;
    }
}
