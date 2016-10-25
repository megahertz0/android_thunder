package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.xunlei.downloadprovidercommon.R;

public class TouchHighlightImageView extends ImageView {
    private Drawable a;
    private Rect b;

    public TouchHighlightImageView(Context context) {
        super(context);
        this.b = new Rect();
        a();
    }

    public TouchHighlightImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new Rect();
        a();
    }

    public TouchHighlightImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Rect();
        a();
    }

    private void a() {
        setBackgroundColor(0);
        setPadding(0, 0, 0, 0);
        this.a = getContext().getResources().getDrawable(R.drawable.selectable_background);
        this.a.setCallback(this);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.a.isStateful()) {
            this.a.setState(getDrawableState());
        }
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, (int) (((float) measuredWidth) * 1.417f));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a.setBounds(this.b);
        this.a.draw(canvas);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.b.set(0, 0, i, i2);
    }
}
