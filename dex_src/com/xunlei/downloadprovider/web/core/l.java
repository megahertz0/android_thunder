package com.xunlei.downloadprovider.web.core;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

// compiled from: ThunderWebView.java
final class l implements OnTouchListener {
    final /* synthetic */ ThunderWebView a;

    l(ThunderWebView thunderWebView) {
        this.a = thunderWebView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.a.a != null) {
            this.a.r = this.a.a.getUrl();
        }
        if (this.a.f != null) {
            this.a.f;
            XLWebView xLWebView = this.a.a;
        }
        return false;
    }
}
