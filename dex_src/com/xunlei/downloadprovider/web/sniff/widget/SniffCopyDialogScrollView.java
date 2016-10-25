package com.xunlei.downloadprovider.web.sniff.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class SniffCopyDialogScrollView extends HorizontalScrollView {
    private static final String a;
    private a b;

    public static interface a {
        void a();

        void b();

        void c();

        void d();
    }

    static {
        a = SniffCopyDialogScrollView.class.getSimpleName();
    }

    public SniffCopyDialogScrollView(Context context) {
        super(context);
    }

    public SniffCopyDialogScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SniffCopyDialogScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnHorizontalScrollListener(a aVar) {
        this.b = aVar;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.b != null) {
            int measuredWidth = getChildAt(0).getMeasuredWidth() - getMeasuredWidth();
            int scrollX = getScrollX();
            if (getScrollX() == 0) {
                this.b.d();
            } else if (getScrollX() == measuredWidth) {
                this.b.c();
            } else if (getScrollX() < scrollX) {
                this.b.b();
            } else {
                this.b.a();
            }
        }
    }
}
