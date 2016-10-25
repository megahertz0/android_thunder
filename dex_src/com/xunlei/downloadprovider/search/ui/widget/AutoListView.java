package com.xunlei.downloadprovider.search.ui.widget;

import android.content.Context;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ListView;

public class AutoListView extends ListView {
    public AutoListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AutoListView(Context context) {
        super(context);
    }

    public AutoListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, ExploreByTouchHelper.INVALID_ID));
    }
}
