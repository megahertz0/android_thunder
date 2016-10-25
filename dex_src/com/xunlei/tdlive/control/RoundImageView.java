package com.xunlei.tdlive.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import com.xunlei.tdlive.R;

public class RoundImageView extends ImageView {
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    private int a;
    private int b;
    private Paint c;
    private int d;
    private Matrix e;
    private BitmapShader f;
    private Drawable g;
    private Bitmap h;
    private int i;
    private RectF j;

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new Matrix();
        this.c = new Paint();
        this.c.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundImageView);
        this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius, (int) TypedValue.applyDimension(TYPE_ROUND, 10.0f, getResources().getDisplayMetrics()));
        this.a = obtainStyledAttributes.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);
        obtainStyledAttributes.recycle();
    }

    public void setBorderRadius(int i) {
        int dp2px = dp2px(i);
        if (this.b != dp2px) {
            this.b = dp2px;
            invalidate();
        }
    }

    public void setType(int i) {
        if (this.a != i) {
            this.a = i;
            if (!(this.a == 1 || this.a == 0)) {
                this.a = 0;
            }
            requestLayout();
        }
    }

    public int dp2px(int i) {
        return (int) TypedValue.applyDimension(TYPE_ROUND, (float) i, getResources().getDisplayMetrics());
    }

    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            a();
            if (this.a == 1) {
                canvas.drawRoundRect(this.j, (float) this.b, (float) this.b, this.c);
            } else {
                canvas.drawCircle((float) this.d, (float) this.d, (float) this.d, this.c);
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.j = new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a == 0) {
            this.i = Math.min(getMeasuredWidth(), getMeasuredHeight());
            this.d = this.i / 2;
            setMeasuredDimension(this.i, this.i);
        }
    }

    private void a() {
        float f = 1.0f;
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap a = a(drawable);
            if (a != null) {
                this.f = new BitmapShader(a, TileMode.CLAMP, TileMode.CLAMP);
                if (this.a == 0) {
                    f = (1.0f * ((float) this.i)) / ((float) Math.min(a.getWidth(), a.getHeight()));
                } else if (this.a == 1) {
                    f = Math.max((((float) getWidth()) * 1.0f) / ((float) a.getWidth()), (1.0f * ((float) getHeight())) / ((float) a.getHeight()));
                }
                this.e.setScale(f, f);
                this.f.setLocalMatrix(this.e);
                this.c.setShader(this.f);
            }
        }
    }

    private Bitmap a(Drawable drawable) {
        try {
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            if (!drawable.equals(this.g)) {
                this.g = drawable;
                if (this.h != null) {
                    this.h.recycle();
                    this.h = null;
                }
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                this.h = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Config.ARGB_8888);
                Canvas canvas = new Canvas(this.h);
                drawable.setBounds(TYPE_CIRCLE, TYPE_CIRCLE, intrinsicWidth, intrinsicHeight);
                drawable.draw(canvas);
            }
            return this.h;
        } catch (Throwable th) {
        }
    }
}
