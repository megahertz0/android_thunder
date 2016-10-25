package com.xunlei.downloadprovider.web.sniff.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class SimpleCHNTextView extends View {
    private Paint a;
    private int b;
    private int c;
    private String d;
    private int e;
    private boolean f;
    private int g;
    private int h;
    private ArrayList<a> i;

    private class a {
        int a;
        int b;

        private a() {
        }
    }

    public SimpleCHNTextView(Context context) {
        super(context);
        a();
    }

    public SimpleCHNTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LabelView);
        setTextColor(obtainStyledAttributes.getColor(1, -16777216));
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(SimpleLog.LOG_LEVEL_DEBUG, 0);
        if (dimensionPixelOffset > 0) {
            setTextSize(dimensionPixelOffset);
        }
        this.e = obtainStyledAttributes.getInt(SimpleLog.LOG_LEVEL_ERROR, Integer.MAX_VALUE);
        this.f = obtainStyledAttributes.getBoolean(MqttConnectOptions.MQTT_VERSION_3_1, false);
        this.g = obtainStyledAttributes.getDimensionPixelOffset(MqttConnectOptions.MQTT_VERSION_3_1_1, 0);
        this.h = obtainStyledAttributes.getInt(SimpleLog.LOG_LEVEL_FATAL, 1);
        CharSequence string = obtainStyledAttributes.getString(0);
        if (string != null) {
            setText(string.toString());
        }
        obtainStyledAttributes.recycle();
    }

    private final void a() {
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setTextSize(16.0f * getResources().getDisplayMetrics().density);
        this.a.setColor(-16777216);
        this.i = new ArrayList();
    }

    public void setText(String str) {
        this.d = str;
        this.i.clear();
        requestLayout();
        invalidate();
    }

    public void setTextSize(int i) {
        this.a.setTextSize((float) i);
        requestLayout();
        invalidate();
    }

    public void setTextColor(int i) {
        this.a.setColor(i);
        invalidate();
    }

    public void setMaxLine(int i) {
        this.e = i;
        invalidate();
    }

    public void setEllipsize(boolean z) {
        this.f = z;
        invalidate();
    }

    public void setLineSpacingExtra(int i) {
        this.g = i;
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        int measureText;
        int i3;
        int length;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (this.d != null) {
            measureText = (((int) this.a.measureText(this.d)) + getPaddingLeft()) + getPaddingRight();
        } else {
            measureText = 0;
        }
        if (mode == 1073741824) {
            i3 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i3 = Math.min(measureText, size);
        } else {
            i3 = measureText;
        }
        int paddingLeft = (i3 - getPaddingLeft()) - getPaddingRight();
        if (paddingLeft > 0 && this.d != null) {
            if (!this.i.isEmpty()) {
                this.i.clear();
            }
            length = this.d.length();
            mode = 0;
            while (mode < length) {
                size = this.a.breakText(this.d, mode, length, true, (float) paddingLeft, null);
                if (size <= 0) {
                    break;
                }
                a aVar = new a();
                aVar.a = mode;
                aVar.b = (mode + size) - 1;
                this.i.add(aVar);
                mode += size;
            }
        }
        measureText = MeasureSpec.getMode(i2);
        size = MeasureSpec.getSize(i2);
        this.b = (int) this.a.ascent();
        this.c = (int) this.a.descent();
        if (measureText != 1073741824) {
            measureText = (int) (((float) (-this.b)) + this.a.descent());
            mode = (getPaddingTop() + measureText) + getPaddingBottom();
            length = Math.min(this.i.size(), this.e);
            if (size == 0) {
                size = Math.min((((measureText * length) + ((length - 1) * this.g)) + getPaddingTop()) + getPaddingBottom(), mode);
            } else {
                size = Math.min((((measureText * length) + ((length - 1) * this.g)) + getPaddingTop()) + getPaddingBottom(), size);
            }
        }
        setMeasuredDimension(i3, size);
    }

    private static String getSuffix$16915f7f() {
        return null;
    }

    protected void onDraw(Canvas canvas) {
        int measuredHeight;
        super.onDraw(canvas);
        int min = Math.min(this.e, this.i.size());
        int i = (this.g * (min - 1)) + (((-this.b) + this.c) * min);
        switch (this.h) {
            case SimpleLog.LOG_LEVEL_DEBUG:
                measuredHeight = (getMeasuredHeight() - i) - getPaddingBottom();
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                measuredHeight = ((((getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) - i) / 2) + getPaddingTop();
                break;
            default:
                measuredHeight = getPaddingTop();
                break;
        }
        int i2 = 0;
        while (i2 < min) {
            a aVar = (a) this.i.get(i2);
            if (!this.f || i2 != min - 1 || getMeasuredWidth() == 0 || this.i.size() <= min) {
                canvas.drawText(this.d, aVar.a, aVar.b + 1, (float) getPaddingLeft(), (float) (((measuredHeight - this.b) + (this.g * i2)) + (((-this.b) + this.c) * i2)), this.a);
            } else {
                String str;
                if (getMeasuredWidth() <= 0) {
                    str = BuildConfig.VERSION_NAME;
                } else {
                    String str2 = BuildConfig.VERSION_NAME;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(this.d.substring(aVar.a, aVar.b + 1));
                    stringBuilder.append(new StringBuilder("....").append(str2).toString());
                    str = stringBuilder.toString();
                    int i3 = aVar.b + 1;
                    while (this.a.measureText(str) > ((float) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()))) {
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append(this.d.substring(aVar.a, i3));
                        stringBuilder.append(new StringBuilder("....").append(str2).toString());
                        str = stringBuilder.toString();
                        i3--;
                    }
                }
                canvas.drawText(str, (float) getPaddingLeft(), (float) (((measuredHeight - this.b) + (this.g * i2)) + (((-this.b) + this.c) * i2)), this.a);
            }
            i2++;
        }
    }
}
