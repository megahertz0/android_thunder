package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.xunlei.common.yunbo.XLYunboVodStatus;
import com.xunlei.tdlive.R;
import java.util.Observable;
import java.util.Observer;
import org.apache.commons.logging.impl.SimpleLog;

public class IJKPlayerSeekBar extends SeekBar implements Observer {
    private Bitmap a;
    private Bitmap b;
    private Canvas c;
    private Paint d;
    private Rect e;
    private int f;
    private int g;
    private final float[] h;

    public IJKPlayerSeekBar(Context context) {
        super(context);
        this.f = Color.rgb(XLYunboVodStatus.ALL, XLYunboVodStatus.ALL, XLYunboVodStatus.ALL);
        this.g = Color.rgb(XLYunboVodStatus.ALL, 240, 0);
        this.h = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
        a(context);
    }

    public IJKPlayerSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = Color.rgb(XLYunboVodStatus.ALL, XLYunboVodStatus.ALL, XLYunboVodStatus.ALL);
        this.g = Color.rgb(XLYunboVodStatus.ALL, 240, 0);
        this.h = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
        a(context);
    }

    public IJKPlayerSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = Color.rgb(XLYunboVodStatus.ALL, XLYunboVodStatus.ALL, XLYunboVodStatus.ALL);
        this.g = Color.rgb(XLYunboVodStatus.ALL, 240, 0);
        this.h = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
        a(context);
    }

    private void a(Context context) {
        this.d = new Paint();
        this.e = new Rect();
        this.b = BitmapFactory.decodeResource(context.getResources(), R.drawable.xllive_seek_bar_thumb);
        this.a = Bitmap.createBitmap(this.b.getWidth() * 2, this.b.getHeight() * 2, this.b.getConfig());
        a();
    }

    private void a() {
        this.c = new Canvas(this.a);
        float[] fArr = new float[]{this.h[0], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.h[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.h[2], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.h[3], 0.0f};
        new ColorMatrix().set(fArr);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(fArr));
        this.c.drawBitmap(this.a, new Matrix(), paint);
    }

    protected synchronized void onDraw(Canvas canvas) {
        this.d.setColor(this.f);
        this.e.set(SimpleLog.LOG_LEVEL_FATAL, (getHeight() / 2) - (this.a.getHeight() / 20), getWidth() - 10, (getHeight() / 2) + (this.a.getHeight() / 20));
        canvas.drawRect(this.e, this.d);
        this.e.set(SimpleLog.LOG_LEVEL_FATAL, (getHeight() / 2) - (this.a.getHeight() / 20), (getProgress() * (getWidth() - 10)) / getMax(), (getHeight() / 2) + (this.a.getHeight() / 20));
        this.d.setColor(this.g);
        canvas.drawRect(this.e, this.d);
        if (getProgress() == getMax()) {
            canvas.drawBitmap(this.a, (float) ((((getProgress() * (getWidth() - 10)) / getMax()) - (this.a.getWidth() / 3)) - 6), (float) ((getHeight() / 2) - (this.a.getHeight() / 2)), this.d);
        } else {
            canvas.drawBitmap(this.a, (float) ((((getProgress() * (getWidth() - 10)) / getMax()) - (this.a.getWidth() / 3)) - 4), (float) ((getHeight() / 2) - (this.a.getHeight() / 2)), this.d);
        }
    }

    public void setDefaultProcessBarRGB(int i) {
        this.f = i;
    }

    public void setSelectedProcessBarRgb(int i) {
        this.g = i;
    }

    public void update(Observable observable, Object obj) {
        invalidate();
    }
}
