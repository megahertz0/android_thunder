package com.xunlei.downloadprovider.xlui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.widget.LinearLayout;

public class KeyLinearLayout extends LinearLayout {
    private OnKeyListener a;

    public KeyLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
    }

    public void setKeyInterceptor(OnKeyListener onKeyListener) {
        this.a = onKeyListener;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = false;
        if (this.a != null) {
            z = this.a.onKey(this, keyEvent.getKeyCode(), keyEvent);
        }
        return z ? true : super.dispatchKeyEvent(keyEvent);
    }
}
