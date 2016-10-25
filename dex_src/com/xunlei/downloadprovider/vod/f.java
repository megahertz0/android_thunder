package com.xunlei.downloadprovider.vod;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

// compiled from: PopupSeekTimeWindow.java
final class f implements OnTouchListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 4) {
            return false;
        }
        this.a.dismiss();
        return true;
    }
}
