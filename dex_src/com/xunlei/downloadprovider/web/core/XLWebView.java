package com.xunlei.downloadprovider.web.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class XLWebView extends WebView {
    public XLWebView(Context context) {
        super(context);
    }

    public XLWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public XLWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public XLWebView(Context context, AttributeSet attributeSet, int i, boolean z) {
        super(context, attributeSet, i, z);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            onScrollChanged(getScrollX(), getScrollY(), getScrollX(), getScrollY());
        }
        return super.onTouchEvent(motionEvent);
    }
}
