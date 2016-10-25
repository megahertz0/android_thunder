package com.xunlei.downloadprovider.web.sniff.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SniffMask extends View {
    private final String a;
    private a b;

    public static interface a {
        void a();
    }

    public SniffMask(Context context) {
        this(context, null);
    }

    public SniffMask(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = "SniffMask";
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b != null) {
            this.b.a();
        }
        return true;
    }

    public void setMaskClickistener(a aVar) {
        this.b = aVar;
    }
}
