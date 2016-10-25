package com.xunlei.tdlive.control;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class CompressibleLayout extends LinearLayout {
    public CompressibleLayout(Context context) {
        this(context, null);
        a();
    }

    public CompressibleLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        a();
    }

    public CompressibleLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        if (size <= 0) {
            throw new IllegalArgumentException("height size must > 0!");
        }
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        if (rect.height() < size) {
            i2 = MeasureSpec.makeMeasureSpec(rect.height(), 1073741824);
        }
        super.onMeasure(i, i2);
    }
}
