package com.umeng.socialize.view.a.b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.lang.ref.WeakReference;

// compiled from: PieView.java
public final class c extends View {
    private RectF a;
    private RectF b;
    private Paint c;
    private Paint d;
    private Paint e;
    private int f;
    private float g;
    private float h;
    private float i;
    private Handler j;

    // compiled from: PieView.java
    private static class a extends Handler {
        WeakReference<c> a;

        public a(c cVar) {
            this.a = new WeakReference(cVar);
        }

        public void handleMessage(Message message) {
            c cVar = (c) this.a.get();
            if (cVar != null) {
                cVar.invalidate();
            }
        }
    }

    public c(Context context, int i, int i2, float f, float f2, float f3, float f4, int i3, int i4, float f5, int i5, float f6) {
        super(context);
        this.j = new a(this);
        this.f = i;
        this.g = f2;
        this.h = f3;
        this.a = new RectF(0.0f, 0.0f, (float) i, (float) i);
        float f7 = (f3 + f4) * ((float) i);
        this.b = new RectF(0.0f + (f7 / 2.0f), 0.0f + (f7 / 2.0f), ((float) i) - (f7 / 2.0f), ((float) i) - (f7 / 2.0f));
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setColor(i2);
        this.c.setAlpha((int) (255.0f * f));
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.d.setStrokeWidth((float) i3);
        this.d.setColor(i4);
        this.d.setAlpha((int) (255.0f * f5));
        this.d.setStyle(Style.STROKE);
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setColor(i5);
        this.e.setAlpha((int) (255.0f * f6));
    }

    public final void a(float f) {
        this.i = f;
        this.j.sendEmptyMessage(0);
    }

    protected final void onMeasure(int i, int i2) {
        setMeasuredDimension(this.f, this.f);
    }

    protected final void onDraw(Canvas canvas) {
        canvas.drawRoundRect(this.a, this.g, this.g, this.c);
        canvas.drawCircle((float) (this.f / 2), (float) (this.f / 2), (((float) this.f) * (1.0f - this.h)) / 2.0f, this.d);
        new StringBuilder("--").append(this.i);
        canvas.drawArc(this.b, -90.0f, this.i, true, this.e);
    }
}
