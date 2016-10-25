package com.xunlei.downloadprovider.web.browser;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import org.android.spdy.SpdyAgent;

// compiled from: InputAutoCompleteView.java
final class ad implements OnTouchListener {
    final /* synthetic */ InputAutoCompleteView a;

    ad(InputAutoCompleteView inputAutoCompleteView) {
        this.a = inputAutoCompleteView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case SpdyAgent.ACCS_TEST_SERVER:
                if (this.a.m != null) {
                    this.a.m.a();
                }
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                view.performClick();
                break;
        }
        return true;
    }
}
