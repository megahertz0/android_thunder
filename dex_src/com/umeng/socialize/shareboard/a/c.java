package com.umeng.socialize.shareboard.a;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

// compiled from: SNSPlatformAdapter.java
class c implements OnTouchListener {
    final /* synthetic */ View a;
    final /* synthetic */ a b;

    c(a aVar, View view) {
        this.b = aVar;
        this.a = view;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.setBackgroundColor(-3355444);
        } else if (motionEvent.getAction() == 1) {
            this.a.setBackgroundColor(-1);
        }
        return false;
    }
}
