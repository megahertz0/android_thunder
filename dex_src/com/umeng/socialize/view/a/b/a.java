package com.umeng.socialize.view.a.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.List;

// compiled from: CustomView.java
public class a extends View {
    private int a;
    private List<Bitmap> b;
    private RectF c;
    private int d;
    private Handler e;

    // compiled from: CustomView.java
    private static class a extends Handler {
        WeakReference<a> a;

        public a(a aVar) {
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            a aVar = (a) this.a.get();
            if (aVar != null) {
                aVar.invalidate();
            }
        }
    }

    public a(Context context, int i, List<Bitmap> list) {
        super(context);
        this.d = 0;
        this.e = new a(this);
        this.a = i;
        this.c = new RectF(0.0f, 0.0f, (float) i, (float) i);
        this.b = list;
    }

    public void a(int i) {
        this.d = i;
        this.e.sendEmptyMessage(0);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.a, this.a);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap((Bitmap) this.b.get(this.d), null, this.c, null);
    }
}
