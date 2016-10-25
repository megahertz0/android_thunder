package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

// compiled from: VerticalImageSpan.java
public final class bd extends ImageSpan {
    private float a;

    public bd(Drawable drawable, float f) {
        super(drawable);
        this.a = f;
    }

    public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        Rect bounds = getDrawable().getBounds();
        if (fontMetricsInt != null) {
            FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            int i3 = fontMetricsInt2.bottom - fontMetricsInt2.top;
            int i4 = bounds.bottom - bounds.top;
            int i5 = (i4 / 2) - (i3 / 4);
            i3 = (i3 / 4) + (i4 / 2);
            fontMetricsInt.ascent = -i3;
            fontMetricsInt.top = -i3;
            fontMetricsInt.bottom = i5;
            fontMetricsInt.descent = i5;
        }
        return bounds.right;
    }

    public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        canvas.save();
        canvas.drawBitmap(bitmap, f, (((float) i5) - ((this.a - ((float) bitmap.getHeight())) / 2.0f)) - ((float) bitmap.getHeight()), paint);
        canvas.restore();
    }
}
