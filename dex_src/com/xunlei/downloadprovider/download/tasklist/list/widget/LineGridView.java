package com.xunlei.downloadprovider.download.tasklist.list.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.GridView;
import com.xunlei.downloadprovider.R;

public class LineGridView extends GridView {
    public LineGridView(Context context) {
        super(context);
    }

    public LineGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LineGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void dispatchDraw(Canvas canvas) {
        int width;
        super.dispatchDraw(canvas);
        View childAt = getChildAt(0);
        if (childAt != null) {
            width = getWidth() / childAt.getWidth();
        } else {
            width = getWidth();
        }
        int childCount = getChildCount();
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(getContext().getResources().getColor(R.color.search_line_color));
        for (int i = 0; i < childCount; i++) {
            View childAt2 = getChildAt(i);
            if (i < 3) {
                canvas.drawLine((float) childAt2.getLeft(), (float) childAt2.getTop(), (float) childAt2.getRight(), (float) childAt2.getTop(), paint);
            }
            if (i % width == 0) {
                canvas.drawLine((float) childAt2.getLeft(), (float) childAt2.getTop(), (float) childAt2.getLeft(), (float) childAt2.getBottom(), paint);
            }
            if ((i + 1) % width == 0) {
                canvas.drawLine((float) childAt2.getLeft(), (float) childAt2.getBottom(), (float) childAt2.getRight(), (float) childAt2.getBottom(), paint);
                canvas.drawLine((float) childAt2.getRight(), (float) childAt2.getTop(), (float) childAt2.getRight(), (float) childAt2.getBottom(), paint);
            } else {
                canvas.drawLine((float) childAt2.getRight(), (float) childAt2.getTop(), (float) childAt2.getRight(), (float) childAt2.getBottom(), paint);
                canvas.drawLine((float) childAt2.getLeft(), (float) childAt2.getBottom(), (float) childAt2.getRight(), (float) childAt2.getBottom(), paint);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        if (getLayoutParams().height == -2) {
            i2 = MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
