package com.xunlei.downloadprovider.web.browser;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.xunlei.downloadprovider.a.b;

// compiled from: InputAutoCompleteView.java
final class ae implements OnTouchListener {
    final /* synthetic */ InputAutoCompleteView a;

    ae(InputAutoCompleteView inputAutoCompleteView) {
        this.a = inputAutoCompleteView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        b.a(this.a.g, view);
        return false;
    }
}
