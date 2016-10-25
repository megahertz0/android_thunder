package com.xunlei.downloadprovider.ad.splash.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import java.lang.reflect.Field;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class CountDownCircleProgressBar extends ProgressBar {
    private final RectF a;
    private final Rect b;
    private final Paint c;
    private final Paint d;
    private final Paint e;
    private final Paint f;
    private float g;
    private float h;
    private float i;
    private int j;
    private int k;
    private float l;
    private float m;
    private float n;
    private int o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private String t;
    private int u;
    private int v;
    private Cap w;

    public CountDownCircleProgressBar(Context context) {
        this(context, null);
    }

    public CountDownCircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new RectF();
        this.b = new Rect();
        this.c = new Paint(1);
        this.d = new Paint(1);
        this.e = new Paint(1);
        this.f = new Paint(1);
        try {
            Field declaredField = ProgressBar.class.getDeclaredField("mOnlyIndeterminate");
            declaredField.setAccessible(true);
            declaredField.set(this, Boolean.valueOf(false));
            declaredField = ProgressBar.class.getDeclaredField("mIndeterminate");
            declaredField.setAccessible(true);
            declaredField.set(this, Boolean.valueOf(false));
            declaredField = ProgressBar.class.getDeclaredField("mCurrentDrawable");
            declaredField.setAccessible(true);
            declaredField.set(this, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CountDownCircleProgressBar);
        this.j = obtainStyledAttributes.getColor(MqttConnectOptions.MQTT_VERSION_3_1, 0);
        this.s = obtainStyledAttributes.getBoolean(1, true);
        this.k = obtainStyledAttributes.getInt(0, com.xunlei.xllib.R.styleable.AppCompatTheme_actionDropDownStyle);
        this.t = obtainStyledAttributes.hasValue(SpdyProtocol.PUBKEY_SEQ_OPEN) ? obtainStyledAttributes.getString(SpdyProtocol.PUBKEY_SEQ_OPEN) : "%d%%";
        this.u = obtainStyledAttributes.getInt(SpdyProtocol.PUBKEY_PSEQ_OPEN, 0);
        this.v = obtainStyledAttributes.getInt(com.xunlei.xllib.R.styleable.Toolbar_titleMargins, 0);
        this.w = obtainStyledAttributes.hasValue(com.xunlei.xllib.R.styleable.Toolbar_titleMarginStart) ? Cap.values()[obtainStyledAttributes.getInt(com.xunlei.xllib.R.styleable.Toolbar_titleMarginStart, 0)] : Cap.BUTT;
        this.l = (float) obtainStyledAttributes.getDimensionPixelSize(SimpleLog.LOG_LEVEL_DEBUG, g.a(getContext(), 4.0f));
        this.n = (float) obtainStyledAttributes.getDimensionPixelSize(SimpleLog.LOG_LEVEL_OFF, g.a(getContext(), 11.0f));
        this.m = (float) obtainStyledAttributes.getDimensionPixelSize(SpdyProtocol.PUBKEY_SEQ_ADASH, g.a(getContext(), 1.0f));
        this.o = obtainStyledAttributes.getColor(MqttConnectOptions.MQTT_VERSION_3_1_1, Color.parseColor("#fff2a670"));
        this.p = obtainStyledAttributes.getColor(SimpleLog.LOG_LEVEL_ERROR, Color.parseColor("#fff2a670"));
        this.q = obtainStyledAttributes.getColor(SimpleLog.LOG_LEVEL_FATAL, Color.parseColor("#fff2a670"));
        this.r = obtainStyledAttributes.getColor(SpdyProtocol.PUBKEY_PSEQ_ADASH, Color.parseColor("#ffe3e3e5"));
        obtainStyledAttributes.recycle();
        this.f.setTextAlign(Align.CENTER);
        this.f.setTextSize(this.n);
        this.c.setStyle(this.u == 1 ? Style.FILL : Style.STROKE);
        this.c.setStrokeWidth(this.m);
        this.c.setColor(this.o);
        this.c.setStrokeCap(this.w);
        this.d.setStyle(this.u == 1 ? Style.FILL : Style.STROKE);
        this.d.setStrokeWidth(this.m);
        this.d.setColor(this.r);
        this.d.setStrokeCap(this.w);
        this.e.setStyle(Style.FILL);
        this.e.setColor(this.j);
    }

    private void a() {
        Shader shader = null;
        if (this.o != this.p) {
            switch (this.v) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    shader = new LinearGradient(this.a.left, this.a.top, this.a.left, this.a.bottom, this.o, this.p, TileMode.CLAMP);
                    break;
                case SimpleLog.LOG_LEVEL_TRACE:
                    shader = new RadialGradient(this.h, this.i, this.g, this.o, this.p, TileMode.CLAMP);
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    double d;
                    float f = (float) (((((double) this.m) / 3.141592653589793d) * 2.0d) / ((double) this.g));
                    if (this.w == Cap.BUTT && this.u == 2) {
                        d = 0.0d;
                    } else {
                        d = Math.toDegrees((double) f);
                    }
                    float f2 = (float) (-90.0d - d);
                    shader = new SweepGradient(this.h, this.i, new int[]{this.o, this.p}, new float[]{0.0f, 1.0f});
                    Matrix matrix = new Matrix();
                    matrix.postRotate(f2, this.h, this.i);
                    shader.setLocalMatrix(matrix);
                    break;
            }
            this.c.setShader(shader);
            return;
        }
        this.c.setShader(null);
        this.c.setColor(this.o);
    }

    protected synchronized void onDraw(Canvas canvas) {
        if (this.j != 0) {
            canvas.drawCircle(this.h, this.h, this.g, this.e);
        }
        switch (this.u) {
            case SimpleLog.LOG_LEVEL_TRACE:
                canvas.drawArc(this.a, -90.0f, 360.0f, false, this.d);
                canvas.drawArc(this.a, -90.0f, (((float) getProgress()) * 360.0f) / ((float) getMax()), true, this.c);
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                canvas.drawArc(this.a, -90.0f, 360.0f, false, this.d);
                canvas.drawArc(this.a, -90.0f, (((float) getProgress()) * 360.0f) / ((float) getMax()), false, this.c);
                break;
            default:
                float f = (float) (6.283185307179586d / ((double) this.k));
                float f2 = this.g;
                float f3 = this.g - this.l;
                int progress = (int) ((((float) getProgress()) / ((float) getMax())) * ((float) this.k));
                for (int i = 0; i < this.k; i++) {
                    float f4 = ((float) i) * f;
                    float sin = this.h + (((float) Math.sin((double) f4)) * f3);
                    float cos = this.h - (((float) Math.cos((double) f4)) * f3);
                    float sin2 = this.h + (((float) Math.sin((double) f4)) * f2);
                    float cos2 = this.h - (((float) Math.cos((double) f4)) * f2);
                    if (i < progress) {
                        canvas.drawLine(sin, cos, sin2, cos2, this.c);
                    } else {
                        canvas.drawLine(sin, cos, sin2, cos2, this.d);
                    }
                }
                break;
        }
        if (this.s) {
            String format = String.format(this.t, new Object[]{Integer.valueOf(getProgress())});
            this.f.setTextSize(this.n);
            this.f.setColor(this.q);
            this.f.getTextBounds(format, 0, format.length(), this.b);
            canvas.drawText(format, this.h, this.i + ((float) (this.b.height() / 2)), this.f);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.h = (float) (i / 2);
        this.i = (float) (i2 / 2);
        this.g = Math.min(this.h, this.i);
        this.a.top = this.i - this.g;
        this.a.bottom = this.i + this.g;
        this.a.left = this.h - this.g;
        this.a.right = this.h + this.g;
        a();
        this.a.inset(this.m / 2.0f, this.m / 2.0f);
    }

    public int getBackgroundColor() {
        return this.j;
    }

    public void setBackgroundColor(int i) {
        this.j = i;
        this.e.setColor(i);
        invalidate();
    }

    public void setProgressTextFormatPattern(String str) {
        this.t = str;
        invalidate();
    }

    public String getProgressTextFormatPattern() {
        return this.t;
    }

    public void setProgressStrokeWidth(float f) {
        this.m = f;
        this.a.inset(this.m / 2.0f, this.m / 2.0f);
        invalidate();
    }

    public float getProgressStrokeWidth() {
        return this.m;
    }

    public void setProgressTextSize(float f) {
        this.n = f;
        invalidate();
    }

    public float getProgressTextSize() {
        return this.n;
    }

    public void setProgressStartColor(int i) {
        this.o = i;
        a();
        invalidate();
    }

    public int getProgressStartColor() {
        return this.o;
    }

    public void setProgressEndColor(int i) {
        this.p = i;
        a();
        invalidate();
    }

    public int getProgressEndColor() {
        return this.p;
    }

    public void setProgressTextColor(int i) {
        this.q = i;
        invalidate();
    }

    public int getProgressTextColor() {
        return this.q;
    }

    public void setProgressBackgroundColor(int i) {
        this.r = i;
        this.d.setColor(this.r);
        invalidate();
    }

    public int getProgressBackgroundColor() {
        return this.r;
    }

    public int getLineCount() {
        return this.k;
    }

    public void setLineCount(int i) {
        this.k = i;
        invalidate();
    }

    public float getLineWidth() {
        return this.l;
    }

    public void setLineWidth(float f) {
        this.l = f;
        invalidate();
    }

    public int getStyle() {
        return this.u;
    }

    public void setStyle(int i) {
        this.u = i;
        this.c.setStyle(this.u == 1 ? Style.FILL : Style.STROKE);
        this.d.setStyle(this.u == 1 ? Style.FILL : Style.STROKE);
        invalidate();
    }

    public int getShader() {
        return this.v;
    }

    public void setShader(int i) {
        this.v = i;
        a();
        invalidate();
    }

    public Cap getCap() {
        return this.w;
    }

    public void setCap(Cap cap) {
        this.w = cap;
        this.c.setStrokeCap(cap);
        this.d.setStrokeCap(cap);
        invalidate();
    }
}
