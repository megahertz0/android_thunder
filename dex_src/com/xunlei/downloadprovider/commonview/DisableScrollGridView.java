package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class DisableScrollGridView extends GridView {
    public DisableScrollGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DisableScrollGridView(Context context) {
        super(context);
    }

    public DisableScrollGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
