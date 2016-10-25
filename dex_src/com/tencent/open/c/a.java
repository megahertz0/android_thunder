package com.tencent.open.c;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

// compiled from: ProGuard
public class a extends RelativeLayout {
    private static final String a;
    private Rect b;
    private boolean c;
    private a d;

    // compiled from: ProGuard
    public static interface a {
        void onKeyboardHidden();

        void onKeyboardShown(int i);
    }

    static {
        a = a.class.getName();
    }

    public a(Context context) {
        super(context);
        this.b = null;
        this.c = false;
        this.d = null;
        if (this.b == null) {
            this.b = new Rect();
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.b.top) - size;
        if (!(this.d == null || size == 0)) {
            if (height > 100) {
                this.d.onKeyboardShown((Math.abs(this.b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                this.d.onKeyboardHidden();
            }
        }
        super.onMeasure(i, i2);
    }
}
