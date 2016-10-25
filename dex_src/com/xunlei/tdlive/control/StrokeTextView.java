package com.xunlei.tdlive.control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class StrokeTextView extends TextView {
    private TextView a;

    public StrokeTextView(Context context) {
        super(context);
        this.a = null;
        init(context, null, 0);
    }

    public StrokeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        init(context, attributeSet, 0);
    }

    public StrokeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        init(context, attributeSet, i);
    }

    public void init(Context context, AttributeSet attributeSet, int i) {
        this.a = new TextView(context, attributeSet, i);
        this.a.getPaint().setStrokeWidth(TypedValue.applyDimension(1, 1.5f, getResources().getDisplayMetrics()));
        this.a.getPaint().setStyle(Style.STROKE);
        this.a.setTextColor(-2917);
        this.a.setGravity(getGravity());
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        this.a.setLayoutParams(layoutParams);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.a.measure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.a.layout(i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        this.a.getPaint().setTextSize(getTextSize());
        this.a.setText(getText());
        this.a.draw(canvas);
        super.onDraw(canvas);
    }
}
