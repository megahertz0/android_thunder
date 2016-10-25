package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.view.Gravity;
import com.bumptech.glide.load.resource.a.b;
import com.tencent.open.yyb.AppbarJsBridge;

// compiled from: GlideBitmapDrawable.java
public class m extends b {
    a a;
    private final Rect b;
    private int c;
    private int d;
    private boolean e;
    private boolean f;

    // compiled from: GlideBitmapDrawable.java
    static class a extends ConstantState {
        private static final Paint d;
        final Bitmap a;
        int b;
        Paint c;

        static {
            d = new Paint(6);
        }

        public a(Bitmap bitmap) {
            this.c = d;
            this.a = bitmap;
        }

        a(a aVar) {
            this(aVar.a);
            this.b = aVar.b;
        }

        final void a() {
            if (d == this.c) {
                this.c = new Paint(6);
            }
        }

        public final Drawable newDrawable() {
            return new m(null, this);
        }

        public final Drawable newDrawable(Resources resources) {
            return new m(resources, this);
        }

        public final int getChangingConfigurations() {
            return 0;
        }
    }

    public m(Resources resources, Bitmap bitmap) {
        this(resources, new a(bitmap));
    }

    m(Resources resources, a aVar) {
        this.b = new Rect();
        if (aVar == null) {
            throw new NullPointerException("BitmapState must not be null");
        }
        int i;
        this.a = aVar;
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
            if (i == 0) {
                i = 160;
            }
            aVar.b = i;
        } else {
            i = aVar.b;
        }
        this.c = aVar.a.getScaledWidth(i);
        this.d = aVar.a.getScaledHeight(i);
    }

    public int getIntrinsicWidth() {
        return this.c;
    }

    public int getIntrinsicHeight() {
        return this.d;
    }

    public final boolean a() {
        return false;
    }

    public final void a(int i) {
    }

    public void start() {
    }

    public void stop() {
    }

    public boolean isRunning() {
        return false;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.e = true;
    }

    public ConstantState getConstantState() {
        return this.a;
    }

    public void draw(Canvas canvas) {
        if (this.e) {
            Gravity.apply(119, this.c, this.d, getBounds(), this.b);
            this.e = false;
        }
        canvas.drawBitmap(this.a.a, null, this.b, this.a.c);
    }

    public void setAlpha(int i) {
        if (this.a.c.getAlpha() != i) {
            a aVar = this.a;
            aVar.a();
            aVar.c.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        a aVar = this.a;
        aVar.a();
        aVar.c.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        Bitmap bitmap = this.a.a;
        return (bitmap == null || bitmap.hasAlpha() || this.a.c.getAlpha() < 255) ? AppbarJsBridge.Code_Java_Exception : -1;
    }

    public Drawable mutate() {
        if (!this.f && super.mutate() == this) {
            this.a = new a(this.a);
            this.f = true;
        }
        return this;
    }
}
