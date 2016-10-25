package com.xunlei.downloadprovider.download.center.widget;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

// compiled from: DownloadMenuPopWindow.java
final class s implements OnTouchListener {
    final /* synthetic */ q a;

    s(q qVar) {
        this.a = qVar;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 4) {
            this.a.dismiss();
        }
        return true;
    }
}
