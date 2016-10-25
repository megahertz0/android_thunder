package com.nostra13.universalimageloader.core.b;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.c.b;
import com.tencent.open.yyb.AppbarJsBridge;

// compiled from: RoundedBitmapDisplayer.java
public final class c implements a {
    protected final int a;
    protected final int b;

    // compiled from: RoundedBitmapDisplayer.java
    public static class a extends Drawable {
        protected final float a;
        protected final int b;
        protected final RectF c;
        protected final RectF d;
        protected final BitmapShader e;
        protected final Paint f;

        public a(Bitmap bitmap, int i, int i2) {
            this.c = new RectF();
            this.a = (float) i;
            this.b = i2;
            this.e = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
            this.d = new RectF((float) i2, (float) i2, (float) (bitmap.getWidth() - i2), (float) (bitmap.getHeight() - i2));
            this.f = new Paint();
            this.f.setAntiAlias(true);
            this.f.setShader(this.e);
            this.f.setFilterBitmap(true);
            this.f.setDither(true);
        }

        protected final void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.c.set((float) this.b, (float) this.b, (float) (rect.width() - this.b), (float) (rect.height() - this.b));
            Matrix matrix = new Matrix();
            matrix.setRectToRect(this.d, this.c, ScaleToFit.FILL);
            this.e.setLocalMatrix(matrix);
        }

        public final void draw(Canvas canvas) {
            canvas.drawRoundRect(this.c, this.a, this.a, this.f);
        }

        public final int getOpacity() {
            return AppbarJsBridge.Code_Java_Exception;
        }

        public final void setAlpha(int i) {
            this.f.setAlpha(i);
        }

        public final void setColorFilter(ColorFilter colorFilter) {
            this.f.setColorFilter(colorFilter);
        }
    }

    public c(int i) {
        this(i, (byte) 0);
    }

    private c(int i, byte b) {
        this.a = i;
        this.b = 0;
    }

    public final void a(Bitmap bitmap, com.nostra13.universalimageloader.core.c.a aVar, LoadedFrom loadedFrom) {
        if (aVar instanceof b) {
            aVar.setImageDrawable(new a(bitmap, this.a, this.b));
            return;
        }
        throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    }
}
