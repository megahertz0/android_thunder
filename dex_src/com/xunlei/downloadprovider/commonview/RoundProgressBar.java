package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.tencent.open.yyb.TitleBar;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovidercommon.R;
import org.android.spdy.SpdyAgent;

public class RoundProgressBar extends View {
    private Paint a;
    private int b;
    private int c;
    private int d;
    private float e;
    private float f;
    private long g;
    private long h;
    private boolean i;
    private String j;
    private int k;
    private RectF l;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundProgressBar);
        this.b = obtainStyledAttributes.getColor(R.styleable.RoundProgressBar_rPbarColor, Color.rgb(239, 241, 247));
        this.c = obtainStyledAttributes.getColor(R.styleable.RoundProgressBar_rPbarProgressColor, Color.rgb(com.xunlei.tdlive.R.styleable.AppCompatTheme_alertDialogStyle, 137, 254));
        this.d = obtainStyledAttributes.getColor(R.styleable.RoundProgressBar_rPbarTextColor, Color.rgb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
        this.e = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_rPbarTextSize, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.f = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_rPbarWidth, 12.0f);
        this.g = (long) obtainStyledAttributes.getInteger(R.styleable.RoundProgressBar_rPbarMax, com.xunlei.tdlive.R.styleable.AppCompatTheme_buttonStyle);
        this.i = obtainStyledAttributes.getBoolean(R.styleable.RoundProgressBar_rPbarTextIsDisplayable, true);
        this.k = obtainStyledAttributes.getInt(R.styleable.RoundProgressBar_rPbarStyle, 0);
        this.h = (long) obtainStyledAttributes.getInt(R.styleable.RoundProgressBar_rPbarProgress, 0);
        setProgress(this.h);
        obtainStyledAttributes.recycle();
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
        new StringBuilder().append(width);
        this.a.setStrokeWidth(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.a.setColor(this.d);
        this.a.setTextSize(this.e);
        this.a.setTypeface(Typeface.DEFAULT);
        if (this.g > 0) {
            int i2 = (int) ((((float) this.h) / ((float) this.g)) * 100.0f);
            if (!TextUtils.isEmpty(this.j)) {
                canvas.drawText(this.j, ((float) width) - (this.a.measureText(this.j) / 2.0f), ((float) width) + (this.e / 2.0f), this.a);
            } else if (this.i && i2 != 0 && this.k == 0) {
                canvas.drawText(i2 + "%", ((float) width) - (this.a.measureText(i2 + "%") / 2.0f), ((float) width) + (this.e / 2.0f), this.a);
            }
            this.a.setStrokeWidth(this.f);
            this.a.setColor(this.c);
            if (this.l == null) {
                this.l = new RectF((float) (width - i), (float) (width - i), (float) (width + i), (float) (width + i));
            }
            switch (this.k) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    this.a.setStyle(Style.STROKE);
                    canvas.drawArc(this.l, -90.0f, (float) ((this.h * 360) / this.g), false, this.a);
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.a.setStyle(Style.FILL_AND_STROKE);
                    if (this.h != 0) {
                        canvas.drawArc(this.l, -90.0f, (float) ((this.h * 360) / this.g), true, this.a);
                    }
                default:
                    break;
            }
        }
    }

    public synchronized long getMax() {
        return this.g;
    }

    public synchronized void setMax(long j) {
        if (j < 0) {
            j = 0;
        }
        this.g = j;
    }

    public synchronized long getProgress() {
        return this.h;
    }

    public synchronized void setProgress(long j) {
        long j2 = 0;
        synchronized (this) {
            if (j >= 0) {
                j2 = j;
            }
            if (j2 > this.g) {
                j2 = this.g;
            }
            if (j2 <= this.g) {
                this.h = j2;
                postInvalidate();
            }
        }
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

    public String getText() {
        return this.j;
    }

    public void setText(String str) {
        this.j = str;
        postInvalidate();
    }
}
