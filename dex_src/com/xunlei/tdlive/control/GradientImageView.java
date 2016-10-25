package com.xunlei.tdlive.control;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GradientImageView extends FrameLayout {
    private ImageView a;
    private ImageView b;
    private int c;
    private int d;
    private int e;
    private int f;

    public GradientImageView(Context context) {
        super(context);
        this.c = 0;
        this.d = 100;
        this.e = 50;
        this.f = 0;
        this.a = new ImageView(context);
        this.b = new ImageView(context);
        this.b.setScaleType(ScaleType.FIT_END);
        a();
    }

    public GradientImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0;
        this.d = 100;
        this.e = 50;
        this.f = 0;
        this.a = new ImageView(context, attributeSet);
        this.b = new ImageView(context, attributeSet);
        this.b.setScaleType(ScaleType.FIT_END);
        a();
    }

    public GradientImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.d = 100;
        this.e = 50;
        this.f = 0;
        this.a = new ImageView(context, attributeSet, i);
        this.b = new ImageView(context, attributeSet, i);
        this.b.setScaleType(ScaleType.FIT_END);
        a();
    }

    @TargetApi(21)
    public GradientImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.c = 0;
        this.d = 100;
        this.e = 50;
        this.f = 0;
        this.a = new ImageView(context, attributeSet, i, i2);
        this.b = new ImageView(context, attributeSet, i, i2);
        this.b.setScaleType(ScaleType.FIT_END);
        a();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f == 0) {
            this.f = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        }
        if (this.b != null) {
            this.b.setLayoutParams(new LayoutParams(-1, (int) (((((float) this.e) * 1.0f) / ((float) (this.d - this.c))) * ((float) this.f)), 80));
        }
    }

    public void setImageBitmap(Bitmap bitmap, Bitmap bitmap2) {
        this.a.setImageBitmap(bitmap);
        this.b.setImageBitmap(bitmap2);
        requestLayout();
    }

    public void setStep(int i) {
        if (i >= this.c && i <= this.d) {
            this.e = i;
            requestLayout();
        }
    }

    public void setRange(int i, int i2) {
        if (i < i2) {
            this.c = i;
            this.d = i2;
            requestLayout();
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.a.setImageDrawable(drawable);
    }

    public void setBackground(Drawable drawable) {
        this.a.setImageDrawable(drawable);
    }

    private void a() {
        addView(this.a, -1, -1);
        addView(this.b, -1, 0);
    }
}
