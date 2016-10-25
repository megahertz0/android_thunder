package com.xunlei.tdlive.control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class RoundProgressBar extends View {
    public static final int FILL = 1;
    public static final int STROKE = 0;
    private Paint a;
    private int b;
    private int c;
    private int d;
    private float e;
    private float f;
    private int g;
    private int h;
    private boolean i;
    private int j;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint();
        this.b = -65536;
        this.c = -16711936;
        this.d = -16711936;
        this.e = 15.0f;
        this.f = 5.0f;
        this.g = 360;
        this.i = false;
        this.j = 1;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int i = (int) (((float) width) - (this.f / 2.0f));
        this.a.setColor(this.b);
        this.a.setStyle(Style.STROKE);
        this.a.setStrokeWidth(this.f);
        this.a.setAntiAlias(true);
        canvas.drawCircle((float) width, (float) width, (float) i, this.a);
        this.a.setStrokeWidth(0.0f);
        this.a.setColor(this.d);
        this.a.setTextSize(this.e);
        this.a.setTypeface(Typeface.DEFAULT_BOLD);
        int i2 = (int) ((((float) this.h) / ((float) this.g)) * 100.0f);
        float measureText = this.a.measureText(i2 + "%");
        if (this.i && i2 != 0 && this.j == 0) {
            canvas.drawText(i2 + "%", ((float) width) - (measureText / 2.0f), ((float) width) + (this.e / 2.0f), this.a);
        }
        this.a.setStrokeWidth(this.f);
        this.a.setColor(this.c);
        RectF rectF = new RectF((float) (width - i), (float) (width - i), (float) (width + i), (float) (width + i));
        switch (this.j) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.a.setStyle(Style.STROKE);
                canvas.drawArc(rectF, 0.0f, (((float) this.h) * 360.0f) / ((float) this.g), false, this.a);
            case FILL:
                this.a.setStyle(Style.FILL_AND_STROKE);
                if (this.h != 0) {
                    canvas.drawArc(rectF, 0.0f, (((float) this.h) * 360.0f) / ((float) this.g), true, this.a);
                }
            default:
                break;
        }
    }

    public int getMax() {
        return this.g;
    }

    public void setMax(int i) {
        if (i >= 0) {
            this.g = i;
            invalidate();
        }
    }

    public int getProgress() {
        return this.h;
    }

    public void setProgress(int i) {
        if (i > this.g) {
            i = this.g;
        }
        this.h = i;
        invalidate();
    }

    public int getCricleColor() {
        return this.b;
    }

    public void setCricleColor(int i) {
        this.b = i;
    }

    public int getCricleProgressColor() {
        return this.c;
    }

    public void setCricleProgressColor(int i) {
        this.c = i;
    }

    public int getTextColor() {
        return this.d;
    }

    public void setTextColor(int i) {
        this.d = i;
    }

    public float getTextSize() {
        return this.e;
    }

    public void setTextSize(float f) {
        this.e = f;
    }

    public float getRoundWidth() {
        return this.f;
    }

    public void setRoundWidth(float f) {
        this.f = f;
    }
}
