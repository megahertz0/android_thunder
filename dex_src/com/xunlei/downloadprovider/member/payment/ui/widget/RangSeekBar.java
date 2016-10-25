package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class RangSeekBar extends ImageView {
    public static final Integer a;
    public static final Integer b;
    private float A;
    private boolean B;
    private Integer C;
    public List<Integer> c;
    private Integer d;
    private Integer e;
    private double f;
    private double g;
    private float h;
    private final int i;
    private RectF j;
    private float k;
    private final Bitmap l;
    private final Paint m;
    private final float n;
    private final float o;
    private final float p;
    private int q;
    private double r;
    private double s;
    private a t;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean y;
    private boolean z;

    public static interface a {
        void a();
    }

    public RangSeekBar(Context context) {
        super(context);
        this.i = 15;
        this.l = BitmapFactory.decodeResource(getResources(), R.drawable.pay_seekbar_thumb_normal);
        this.m = new Paint(1);
        this.n = (float) this.l.getWidth();
        this.o = this.n * 0.5f;
        this.p = ((float) this.l.getHeight()) * 0.5f;
        this.r = 0.0d;
        this.s = 1.0d;
        this.u = g.a(getContext(), 8.0f);
        this.y = false;
        this.z = false;
        this.B = false;
        this.C = Integer.valueOf(0);
        this.c = new ArrayList();
        a(context, null);
    }

    public RangSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 15;
        this.l = BitmapFactory.decodeResource(getResources(), R.drawable.pay_seekbar_thumb_normal);
        this.m = new Paint(1);
        this.n = (float) this.l.getWidth();
        this.o = this.n * 0.5f;
        this.p = ((float) this.l.getHeight()) * 0.5f;
        this.r = 0.0d;
        this.s = 1.0d;
        this.u = g.a(getContext(), 8.0f);
        this.y = false;
        this.z = false;
        this.B = false;
        this.C = Integer.valueOf(0);
        this.c = new ArrayList();
        a(context, attributeSet);
    }

    public RangSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 15;
        this.l = BitmapFactory.decodeResource(getResources(), R.drawable.pay_seekbar_thumb_normal);
        this.m = new Paint(1);
        this.n = (float) this.l.getWidth();
        this.o = this.n * 0.5f;
        this.p = ((float) this.l.getHeight()) * 0.5f;
        this.r = 0.0d;
        this.s = 1.0d;
        this.u = g.a(getContext(), 8.0f);
        this.y = false;
        this.z = false;
        this.B = false;
        this.C = Integer.valueOf(0);
        this.c = new ArrayList();
        a(context, attributeSet);
    }

    static {
        a = Integer.valueOf(0);
        b = Integer.valueOf(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            this.d = a;
            this.e = b;
            a();
        } else {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RangeSeekBar, 0, 0);
            a(a(obtainStyledAttributes, 0, a.intValue()), a(obtainStyledAttributes, 1, b.intValue()));
            obtainStyledAttributes.recycle();
        }
        this.h = (float) g.a(context, 2.0f);
        this.v = g.a(context, 11.0f);
        this.w = g.a(context, 6.0f);
        this.x = (this.v + g.a(context, 8.0f)) + this.w;
        float a = (float) g.a(context, 15.0f);
        this.j = new RectF(this.k, (((float) this.x) + this.p) - (a / 2.0f), ((float) getWidth()) - this.k, (a / 2.0f) + (((float) this.x) + this.p));
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.q = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public final void a(Integer num, Integer num2) {
        int i = 0;
        this.d = num;
        this.e = num2;
        a();
        this.c.clear();
        int intValue = this.e.intValue() / 31;
        if (intValue == 0) {
            setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.c.add(this.e);
        } else if (this.e.intValue() % 31 == 0) {
            this.c.add(Integer.valueOf(0));
            while (i < intValue) {
                this.c.add(Integer.valueOf((i + 1) * 31));
                i++;
            }
        } else if (this.e.intValue() % 31 != 0) {
            this.c.add(Integer.valueOf(0));
            while (i < intValue) {
                if (i + 1 != intValue) {
                    this.c.add(Integer.valueOf((i + 1) * 31));
                } else {
                    this.c.add(this.e);
                }
                i++;
            }
        }
        invalidate();
    }

    private void a() {
        this.f = this.d.doubleValue();
        this.g = this.e.doubleValue();
    }

    private static Integer a(TypedArray typedArray, int i, int i2) {
        return typedArray.peekValue(i) == null ? Integer.valueOf(i2) : Integer.valueOf(typedArray.getInteger(i, i2));
    }

    private Integer a(double d) {
        return Integer.valueOf((int) (((double) Math.round((this.f + ((this.g - this.f) * d)) * 100.0d)) / 100.0d));
    }

    public void setOnRangeSeekBarChangeListener(a aVar) {
        this.t = aVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.A = motionEvent.getX();
                setPressed(true);
                this.B = true;
                b();
                this.z = false;
                break;
            case SimpleLog.LOG_LEVEL_TRACE:
                float x = motionEvent.getX();
                this.y = true;
                if (this.B) {
                    setNormalizedMaxValue(a(x));
                    this.B = false;
                    setPressed(false);
                } else {
                    this.B = true;
                    setNormalizedMaxValue(a(x));
                    this.B = false;
                }
                invalidate();
                if (this.t != null) {
                    a aVar = this.t;
                    this.C.intValue();
                    aVar.a();
                }
                this.z = false;
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                float x2 = motionEvent.getX();
                if (this.B) {
                    b(a(x2));
                } else if (Math.abs(x2 - this.A) > ((float) this.q)) {
                    setPressed(true);
                    invalidate();
                    this.B = true;
                    b(a(x2));
                    b();
                }
                this.z = true;
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (this.B) {
                    this.B = false;
                    setPressed(false);
                }
                invalidate();
                this.z = false;
                break;
        }
        return true;
    }

    private void b(double d) {
        this.s = Math.max(0.0d, Math.min(1.0d, Math.max(d, this.r)));
        invalidate();
    }

    private void setNormalizedMaxValue(double d) {
        int a;
        double d2;
        if (a(d).intValue() > this.C.intValue()) {
            a = a(a(d).intValue(), true);
        } else {
            int a2 = a(a(d).intValue(), false);
            a = a2 == ((Integer) this.c.get(0)).intValue() ? ((Integer) this.c.get(1)).intValue() : a2;
        }
        this.C = Integer.valueOf(a);
        Integer valueOf = Integer.valueOf(a);
        if (0.0d == this.g - this.f) {
            d2 = 0.0d;
        } else {
            d2 = (valueOf.doubleValue() - this.f) / (this.g - this.f);
        }
        this.s = Math.max(0.0d, Math.min(1.0d, Math.max(d2, this.r)));
        invalidate();
    }

    private double a(float f) {
        int width = getWidth();
        return ((float) width) <= this.k * 2.0f ? 0.0d : Math.min(1.0d, Math.max(0.0d, (double) ((f - this.k) / (((float) width) - (this.k * 2.0f)))));
    }

    private float c(double d) {
        return (float) (((double) this.k) + (((double) (((float) getWidth()) - (2.0f * this.k))) * d));
    }

    private void b() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = XLRegErrorCode.REG_SUCCEED;
        if (MeasureSpec.getMode(i) != 0) {
            i3 = MeasureSpec.getSize(i);
        }
        int height = this.l.getHeight() + (g.a(getContext(), 13.0f) * 2);
        if (MeasureSpec.getMode(i2) != 0) {
            height = Math.min(height, MeasureSpec.getSize(i2));
        }
        setMeasuredDimension(i3, height);
    }

    protected void onDraw(Canvas canvas) {
        this.m.setTextSize((float) this.v);
        this.m.setStyle(Style.FILL);
        this.m.setColor(-1117969);
        this.m.setAntiAlias(true);
        this.k = this.h + this.o;
        this.j.left = this.k;
        this.j.right = ((float) getWidth()) - this.k;
        canvas.drawRoundRect(this.j, (float) this.u, (float) this.u, this.m);
        this.j.left = c(this.r);
        this.j.right = c(this.s);
        this.m.setColor(-15559434);
        canvas.drawRoundRect(this.j, (float) this.u, (float) this.u, this.m);
        float c = c(this.s);
        Bitmap bitmap = this.l;
        if (getCurrentStepIndex() != 0) {
            if (getCurrentStepIndex() == this.c.size() - 1) {
                c -= 2.0f * this.o;
            } else {
                c -= this.o;
            }
        }
        canvas.drawBitmap(bitmap, c, (float) this.x, this.m);
        if (!(getCurrentStepIndex() == 0 || this.y || this.z)) {
            this.y = true;
        }
        if (this.y && getCurrentStepIndex() != 0) {
            String str;
            this.m.setTextSize((float) this.v);
            this.m.setColor(-15559434);
            int a = g.a(getContext(), 4.0f);
            if (getCurrentStepIndex() == this.c.size() - 1) {
                str = "\u5168\u90e8";
            } else {
                str = getCurrentStepIndex() + "\u4e2a\u6708";
            }
            float measureText = this.m.measureText(str) - ((float) a);
            if (getCurrentStepIndex() == this.c.size() - 1) {
                measureText = c(this.s) - measureText;
            } else {
                measureText = c(this.s) - (measureText * 0.5f);
            }
            canvas.drawText(str, measureText, (float) (this.w + this.v), this.m);
            this.y = false;
        }
        super.onDraw(canvas);
    }

    public void setCurrentCoordValue(int i) {
        int i2;
        if (i != -1 || this.c.size() <= 0) {
            i2 = i * 31;
            if (i2 > this.e.intValue()) {
                i2 = this.e.intValue();
            }
            if (i2 == 0 && this.c != null && this.c.size() > 0) {
                i2 = ((Integer) this.c.get(0)).intValue();
            }
        } else {
            i2 = ((Integer) this.c.get(this.c.size() - 1)).intValue();
        }
        this.C = Integer.valueOf(i2);
        this.y = true;
        b(((double) i2) / ((double) this.e.intValue()));
    }

    public int getCurrentCoordValue() {
        return this.C.intValue();
    }

    private int a(int i, boolean z) {
        int i2 = 0;
        if (i >= ((Integer) this.c.get(this.c.size() - 1)).intValue()) {
            return ((Integer) this.c.get(this.c.size() - 1)).intValue();
        }
        if (i <= ((Integer) this.c.get(1)).intValue()) {
            return ((Integer) this.c.get(0)).intValue();
        }
        int intValue = this.C.intValue();
        while (i2 < this.c.size()) {
            int intValue2 = ((Integer) this.c.get(i2)).intValue();
            int intValue3;
            if (z) {
                if (i2 == this.c.size() - 1) {
                    intValue3 = ((Integer) this.c.get(i2)).intValue() - ((Integer) this.c.get(i2 - 1)).intValue();
                } else {
                    intValue3 = 31;
                }
                if (intValue2 - i < intValue3 && intValue2 > i) {
                    return intValue2;
                }
            } else {
                if (i2 == this.c.size() - 2) {
                    intValue3 = ((Integer) this.c.get(i2 + 1)).intValue() - ((Integer) this.c.get(i2)).intValue();
                } else {
                    intValue3 = 31;
                }
                if (i - intValue2 < intValue3 && intValue2 < i) {
                    return intValue2;
                }
            }
            i2++;
        }
        return intValue;
    }

    private int getCurrentStepIndex() {
        for (int i = 0; i < this.c.size(); i++) {
            if (this.C.equals(this.c.get(i))) {
                return i;
            }
        }
        return 0;
    }
}
