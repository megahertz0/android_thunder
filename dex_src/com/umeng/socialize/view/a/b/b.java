package com.umeng.socialize.view.a.b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.List;

// compiled from: FlowerView.java
public final class b extends View {
    private int a;
    private int b;
    private int c;
    private float d;
    private RectF e;
    private Paint f;
    private Paint g;
    private Paint h;
    private List<com.umeng.socialize.view.a.a.b> i;
    private int[] j;
    private Handler k;
    private int l;
    private String m;
    private int n;
    private int o;
    private int p;
    private boolean q;

    // compiled from: FlowerView.java
    private static class a extends Handler {
        WeakReference<b> a;

        public a(b bVar) {
            this.a = new WeakReference(bVar);
        }

        public void handleMessage(Message message) {
            b bVar = (b) this.a.get();
            if (bVar != null) {
                bVar.invalidate();
            }
        }
    }

    public b(Context context, int i, int i2, float f, float f2, int i3, int i4, float f3, float f4, float f5, int i5, int i6, String str, float f6, int i7, float f7, int i8, boolean z) {
        super(context);
        this.k = new a(this);
        this.p = i8;
        a(i, i2, f, f2, i3, i4, f3, f4, f5, i5, i6, str, f6, i7, f7, z);
    }

    private void a(int i, int i2, float f, float f2, int i3, int i4, float f3, float f4, float f5, int i5, int i6, String str, float f6, int i7, float f7, boolean z) {
        boolean z2 = (str == null || str.length() == 0 || !z) ? false : true;
        this.q = z2;
        this.a = i;
        this.c = i4;
        this.d = f2;
        this.f = new Paint();
        this.f.setAntiAlias(true);
        this.f.setColor(i2);
        this.f.setAlpha((int) (255.0f * f));
        this.g = new Paint();
        this.g.setAntiAlias(true);
        this.g.setStrokeWidth((float) i3);
        this.g.setStrokeCap(Cap.ROUND);
        if (str == null || str.length() == 0) {
            this.p = 0;
        } else {
            this.m = str;
            this.h = new Paint();
            this.h.setAntiAlias(true);
            this.h.setColor(i7);
            this.h.setAlpha((int) (255.0f * f7));
            this.h.setTextSize(f6);
            Rect rect = new Rect();
            this.h.getTextBounds(str, 0, str.length(), rect);
            this.n = rect.bottom - rect.top;
            this.o = rect.right - rect.left;
        }
        if (this.q) {
            this.e = new RectF(0.0f, 0.0f, (float) ((this.a + this.n) + this.p), (float) ((this.a + this.n) + this.p));
            this.b = (this.a + this.n) + this.p;
        } else {
            this.e = new RectF(0.0f, 0.0f, (float) this.a, (float) ((this.a + this.n) + this.p));
            this.b = this.a;
        }
        com.umeng.socialize.view.a.a.a aVar = new com.umeng.socialize.view.a.a.a(i4);
        this.i = aVar.a(this.a, (int) (((float) this.a) * f4), (int) (((float) this.a) * f5), i4, this.b);
        this.j = aVar.a(i5, i6, i4, (int) (255.0f * f3));
    }

    public final void a(int i) {
        this.l = i;
        this.k.sendEmptyMessage(0);
    }

    protected final void onMeasure(int i, int i2) {
        if (this.q) {
            setMeasuredDimension((this.a + this.n) + this.p, (this.a + this.n) + this.p);
        } else {
            setMeasuredDimension(this.a, (this.a + this.n) + this.p);
        }
    }

    protected final void onDraw(Canvas canvas) {
        canvas.drawRoundRect(this.e, this.d, this.d, this.f);
        for (int i = 0; i < this.c; i++) {
            com.umeng.socialize.view.a.a.b bVar = (com.umeng.socialize.view.a.a.b) this.i.get(i);
            this.g.setColor(this.j[(this.l + i) % this.c]);
            canvas.drawLine((float) bVar.a(), (float) bVar.b(), (float) bVar.c(), (float) bVar.d(), this.g);
        }
        if (this.m != null) {
            canvas.drawText(this.m, (float) ((this.b / 2) - (this.o / 2)), (float) this.a, this.h);
        }
    }
}
