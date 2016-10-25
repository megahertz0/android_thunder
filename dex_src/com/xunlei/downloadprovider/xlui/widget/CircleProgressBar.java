package com.xunlei.downloadprovider.xlui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class CircleProgressBar extends View {
    private Drawable a;
    private Drawable b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private SweepGradient i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private Paint r;
    private RectF s;
    private boolean t;

    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = false;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 1;
        this.r = null;
        this.s = null;
        this.t = true;
        setWillNotDraw(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.d = 100;
        this.e = 0;
        this.f = 0;
        this.k = 24;
        this.m = (int) TypedValue.applyDimension(1, 60.0f, displayMetrics);
        this.l = 24;
        this.n = (int) TypedValue.applyDimension(1, 60.0f, displayMetrics);
        this.o = g.a(getContext(), 4.0f);
        this.g = getResources().getColor(R.color.blue_hover);
        this.h = getResources().getColor(R.color.blue_mormal);
        this.c = getResources().getColor(R.color.download_list_operate_bg);
        this.p = 0;
        this.q = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleProgressBar, i, 0);
        this.k = obtainStyledAttributes.getDimensionPixelSize(SimpleLog.LOG_LEVEL_DEBUG, this.k);
        this.l = obtainStyledAttributes.getDimensionPixelSize(1, this.l);
        this.m = obtainStyledAttributes.getDimensionPixelSize(MqttConnectOptions.MQTT_VERSION_3_1_1, this.m);
        this.n = obtainStyledAttributes.getDimensionPixelSize(MqttConnectOptions.MQTT_VERSION_3_1, this.n);
        this.o = obtainStyledAttributes.getDimensionPixelSize(SimpleLog.LOG_LEVEL_ERROR, this.o);
        this.c = obtainStyledAttributes.getColor(com.xunlei.xllib.R.styleable.Toolbar_titleMargins, this.c);
        this.g = obtainStyledAttributes.getColor(SimpleLog.LOG_LEVEL_FATAL, this.g);
        this.h = obtainStyledAttributes.getColor(SimpleLog.LOG_LEVEL_OFF, this.h);
        this.p = obtainStyledAttributes.getInt(0, this.p);
        Drawable drawable = obtainStyledAttributes.getDrawable(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (drawable != null) {
            setImageDrawable(drawable);
        }
        setMax(obtainStyledAttributes.getInt(SpdyProtocol.PUBKEY_PSEQ_ADASH, this.d));
        setProgress(obtainStyledAttributes.getInt(SpdyProtocol.PUBKEY_SEQ_OPEN, this.e));
        setSecondaryProgress(obtainStyledAttributes.getInt(SpdyProtocol.PUBKEY_PSEQ_OPEN, this.f));
        obtainStyledAttributes.recycle();
    }

    public int getMax() {
        return this.d;
    }

    public void setMax(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i != this.d) {
            this.d = i;
            if (this.e > i) {
                this.e = i;
            }
            if (this.f > i) {
                this.f = i;
            }
            postInvalidate();
        }
    }

    public synchronized int getProgress() {
        return this.e;
    }

    public synchronized void setProgress(int i) {
        int i2;
        if (i < 0) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (i2 > this.d) {
            i2 = this.d;
        }
        if (i2 != this.e) {
            this.e = i2;
            postInvalidate();
        }
    }

    public synchronized int getSecondaryProgress() {
        return this.f;
    }

    public synchronized void setSecondaryProgress(int i) {
        int i2;
        if (i < 0) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (i2 > this.d) {
            i2 = this.d;
        }
        if (i2 != this.f) {
            this.f = i2;
            postInvalidate();
        }
    }

    public void setBackgroundResource(int i) {
        setBackgroundDrawable(getResources().getDrawable(i));
    }

    public void setImageResource(int i) {
        setImageDrawable(getResources().getDrawable(i));
    }

    public void setProgressColor(int i) {
        this.g = getResources().getColor(i);
        postInvalidate();
    }

    public void setSecondaryProgressColor(int i) {
        this.h = getResources().getColor(i);
        postInvalidate();
    }

    public void setDrawProgress(boolean z) {
        this.t = z;
    }

    public void setUseGradient(boolean z) {
        this.j = z;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Object obj = null;
        if (!(this.a == null || drawable == this.a)) {
            this.a.setCallback(null);
            obj = 1;
        }
        this.a = drawable;
        if (obj != null) {
            a(getWidth(), getHeight());
            invalidate();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        Object obj;
        if (this.b == null || drawable == this.b) {
            obj = null;
        } else {
            this.b.setCallback(null);
            obj = 1;
        }
        this.b = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            int measuredWidth = getMeasuredWidth();
            if (measuredWidth != 0 && measuredWidth < drawable.getMinimumWidth()) {
                requestLayout();
            }
        }
        if (obj != null) {
            b(getWidth(), getHeight());
            a();
            invalidate();
        }
    }

    private void a(int i, int i2) {
        if (this.a != null) {
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            this.a.setBounds(0, 0, (i - paddingRight) - paddingLeft, (i2 - getPaddingBottom()) - getPaddingTop());
        }
    }

    private void b(int i, int i2) {
        if (this.b != null) {
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            paddingLeft = ((i - paddingRight) - paddingLeft) - (this.o * 2);
            paddingRight = ((i2 - getPaddingBottom()) - getPaddingTop()) - (this.o * 2);
            int intrinsicWidth = this.b.getIntrinsicWidth();
            int intrinsicHeight = this.b.getIntrinsicHeight();
            if (intrinsicWidth > paddingLeft || intrinsicHeight > paddingRight) {
                this.b.setBounds(0, 0, paddingLeft, paddingRight);
            } else {
                this.b.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            }
        }
    }

    protected void drawableStateChanged() {
        a();
    }

    private void a() {
        if (this.b != null && this.b.isStateful()) {
            this.b.setState(getDrawableState());
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.b;
    }

    public void invalidateDrawable(Drawable drawable) {
        if (verifyDrawable(drawable)) {
            Rect bounds = drawable.getBounds();
            int measuredWidth = getMeasuredWidth();
            measuredWidth = ((((measuredWidth - bounds.width()) - getPaddingLeft()) - getPaddingRight()) / 2) + (getScrollX() + getPaddingLeft());
            int scrollY = getScrollY() + getPaddingTop();
            int measuredHeight = ((((getMeasuredHeight() - bounds.height()) - getPaddingTop()) - getPaddingBottom()) / 2) + scrollY;
            invalidate(bounds.left + measuredWidth, bounds.top + measuredHeight, measuredWidth + bounds.right, bounds.bottom + measuredHeight);
            return;
        }
        super.invalidateDrawable(drawable);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        a(i, i2);
        b(i, i2);
    }

    private Paint getDrawPaint() {
        if (this.r == null) {
            this.r = new Paint();
        }
        return this.r;
    }

    private RectF getDrawRectF() {
        if (this.s == null) {
            this.s = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        return this.s;
    }

    private RectF c(int i, int i2) {
        RectF drawRectF = getDrawRectF();
        drawRectF.set(-1.0f, -1.0f, (float) i, (float) i2);
        return drawRectF;
    }

    protected void onDraw(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int measuredWidth = getMeasuredWidth();
        if (this.t) {
            canvas.save();
            canvas.rotate(-90.0f, ((float) measuredWidth) / 2.0f, ((float) measuredWidth) / 2.0f);
            canvas.translate((float) paddingLeft, (float) paddingTop);
            float f = (this.d > 0 ? ((float) this.e) / ((float) this.d) : 0.0f) * 360.0f;
            RectF drawRectF = getDrawRectF();
            Paint drawPaint = getDrawPaint();
            drawPaint.setAntiAlias(true);
            drawPaint.setColor(this.c);
            drawPaint.setShader(null);
            drawPaint.setStyle(Style.STROKE);
            drawPaint.setStrokeWidth(5.0f);
            canvas.drawArc(drawRectF, 0.0f, 360.0f, true, drawPaint);
            if (0.0f < f) {
                int i = (measuredWidth / 2) - paddingLeft;
                drawPaint = getDrawPaint();
                drawPaint.setAntiAlias(true);
                if (this.j) {
                    drawPaint.setColor(-1);
                    if (this.i == null) {
                        this.i = new SweepGradient((float) i, (float) i, getResources().getColor(R.color.download_list_task_progress_firstacc), getResources().getColor(R.color.download_list_task_progress_secondacc));
                    }
                    drawPaint.setShader(this.i);
                } else {
                    drawPaint.setShader(null);
                    drawPaint.setColor(this.g);
                }
                drawPaint.setStyle(Style.STROKE);
                drawPaint.setStrokeWidth(5.0f);
                canvas.drawArc(drawRectF, 0.0f, f, false, drawPaint);
            }
            canvas.restore();
        }
        if (this.b != null) {
            canvas.save();
            Rect bounds = this.b.getBounds();
            canvas.translate(((float) paddingLeft) + (((float) (((measuredWidth - bounds.width()) - paddingLeft) - getPaddingRight())) / 2.0f), (((float) (((getMeasuredHeight() - bounds.height()) - paddingTop) - getPaddingBottom())) / 2.0f) + ((float) paddingTop));
            this.b.draw(canvas);
            canvas.restore();
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        Drawable drawable;
        int intrinsicWidth;
        if (1 == this.q) {
            drawable = this.b;
            if (drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
                i3 = drawable.getIntrinsicHeight();
                if (intrinsicWidth <= i3) {
                    i3 = intrinsicWidth;
                }
            }
            i3 += this.o * 2;
            intrinsicWidth = Math.max(this.k, Math.min(this.m, i3));
            i3 = Math.max(this.l, Math.min(this.n, i3));
            int min = Math.min(intrinsicWidth, i3);
            c(min + 1, min + 1);
            a();
            setMeasuredDimension(resolveSize(intrinsicWidth + (getPaddingLeft() + getPaddingRight()), MeasureSpec.makeMeasureSpec(XLYunboMassage.MSG_TASKCREATED, Integer.MIN_VALUE)), resolveSize(i3 + (getPaddingTop() + getPaddingBottom()), MeasureSpec.makeMeasureSpec(XLYunboMassage.MSG_TASKCREATED, Integer.MIN_VALUE)));
            return;
        }
        drawable = this.a;
        if (drawable != null) {
            intrinsicWidth = drawable.getIntrinsicWidth();
            i3 = drawable.getIntrinsicHeight();
            if (intrinsicWidth <= i3) {
                i3 = intrinsicWidth;
            }
        }
        intrinsicWidth = Math.max(this.k, Math.min(this.m, i3));
        i3 = Math.max(this.l, Math.min(this.n, i3));
        min = Math.min(intrinsicWidth, i3);
        c(min + 1, min + 1);
        a();
        setMeasuredDimension((intrinsicWidth + (getPaddingLeft() + getPaddingRight())) & 16777215, (i3 + (getPaddingTop() + getPaddingBottom())) & 16777215);
    }
}
