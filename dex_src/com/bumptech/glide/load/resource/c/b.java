package com.bumptech.glide.load.resource.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.view.Gravity;
import com.bumptech.glide.b.c;
import com.bumptech.glide.load.f;
import com.uc.addon.sdk.remote.Tabs;

// compiled from: GifDrawable.java
public class b extends com.bumptech.glide.load.resource.a.b implements com.bumptech.glide.load.resource.c.f.b {
    public final a a;
    public final com.bumptech.glide.b.a b;
    final f c;
    boolean d;
    private final Paint e;
    private final Rect f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private boolean l;

    // compiled from: GifDrawable.java
    static class a extends ConstantState {
        c a;
        byte[] b;
        Context c;
        f<Bitmap> d;
        int e;
        int f;
        com.bumptech.glide.b.a.a g;
        com.bumptech.glide.load.engine.a.c h;
        public Bitmap i;

        public a(c cVar, byte[] bArr, Context context, f<Bitmap> fVar, int i, int i2, com.bumptech.glide.b.a.a aVar, com.bumptech.glide.load.engine.a.c cVar2, Bitmap bitmap) {
            if (bitmap == null) {
                throw new NullPointerException("The first frame of the GIF must not be null");
            }
            this.a = cVar;
            this.b = bArr;
            this.h = cVar2;
            this.i = bitmap;
            this.c = context.getApplicationContext();
            this.d = fVar;
            this.e = i;
            this.f = i2;
            this.g = aVar;
        }

        public final Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        public final Drawable newDrawable() {
            return new b(this);
        }

        public final int getChangingConfigurations() {
            return 0;
        }
    }

    public b(Context context, com.bumptech.glide.b.a.a aVar, com.bumptech.glide.load.engine.a.c cVar, f<Bitmap> fVar, int i, int i2, c cVar2, byte[] bArr, Bitmap bitmap) {
        this(new a(cVar2, bArr, context, fVar, i, i2, aVar, cVar, bitmap));
    }

    b(a aVar) {
        this.f = new Rect();
        this.i = true;
        this.k = -1;
        if (aVar == null) {
            throw new NullPointerException("GifState must not be null");
        }
        this.a = aVar;
        this.b = new com.bumptech.glide.b.a(aVar.g);
        this.e = new Paint();
        this.b.a(aVar.a, aVar.b);
        this.c = new f(aVar.c, this, this.b, aVar.e, aVar.f);
    }

    public void start() {
        this.h = true;
        this.j = 0;
        if (this.i) {
            c();
        }
    }

    public void stop() {
        this.h = false;
        d();
        if (VERSION.SDK_INT < 11) {
            b();
        }
    }

    private void b() {
        this.c.a();
        invalidateSelf();
    }

    private void c() {
        if (this.b.e.c != 1) {
            if (!this.g) {
                this.g = true;
                f fVar = this.c;
                if (!fVar.c) {
                    fVar.c = true;
                    fVar.g = false;
                    fVar.b();
                }
            } else {
                return;
            }
        }
        invalidateSelf();
    }

    private void d() {
        this.g = false;
        this.c.c = false;
    }

    public boolean setVisible(boolean z, boolean z2) {
        this.i = z;
        if (!z) {
            d();
        } else if (this.h) {
            c();
        }
        return super.setVisible(z, z2);
    }

    public int getIntrinsicWidth() {
        return this.a.i.getWidth();
    }

    public int getIntrinsicHeight() {
        return this.a.i.getHeight();
    }

    public boolean isRunning() {
        return this.g;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.l = true;
    }

    public void draw(Canvas canvas) {
        if (!this.d) {
            Bitmap bitmap;
            if (this.l) {
                Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), this.f);
                this.l = false;
            }
            f fVar = this.c;
            if (fVar.f != null) {
                bitmap = fVar.f.b;
            } else {
                bitmap = null;
            }
            if (bitmap == null) {
                bitmap = this.a.i;
            }
            canvas.drawBitmap(bitmap, null, this.f, this.e);
        }
    }

    public void setAlpha(int i) {
        this.e.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return Tabs.TAB_CREATE_REACH_MAX_COUNT;
    }

    @TargetApi(11)
    public final void b(int i) {
        if (VERSION.SDK_INT < 11 || getCallback() != null) {
            invalidateSelf();
            if (i == this.b.e.c - 1) {
                this.j++;
            }
            if (this.k != -1 && this.j >= this.k) {
                stop();
                return;
            }
            return;
        }
        stop();
        b();
    }

    public ConstantState getConstantState() {
        return this.a;
    }

    public final boolean a() {
        return true;
    }

    public final void a(int i) {
        if (i <= 0 && i != -1 && i != 0) {
            throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
        } else if (i == 0) {
            this.k = this.b.e.m;
        } else {
            this.k = i;
        }
    }
}
