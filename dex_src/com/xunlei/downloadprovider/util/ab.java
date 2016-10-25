package com.xunlei.downloadprovider.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

// compiled from: UtilUI.java
public final class ab {
    public static Drawable a(Bitmap bitmap, Bitmap bitmap2, Resources resources) {
        if (bitmap == null) {
            return new BitmapDrawable(resources, bitmap2);
        }
        if (bitmap2 == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        int i = ((int) (((float) width) * 1.0f)) + 3;
        float f = width2 > height2 ? ((float) i) / ((float) height2) : ((float) i) / ((float) width2);
        float f2 = (((float) width) - (((float) width2) * f)) * 0.5f;
        float f3 = (((float) height) - (((float) height2) * f)) * 0.5f;
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        matrix.postTranslate((float) ((int) (f2 + 0.5f)), (float) ((int) (f3 + 0.5f)));
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        canvas.drawCircle(((float) width) / 2.0f, ((float) height) / 2.0f, (((float) width) * 1.0f) / 2.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, paint);
        paint.setXfermode(null);
        Matrix matrix2 = new Matrix();
        if (matrix.invert(matrix2)) {
            canvas.concat(matrix2);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        }
        return new BitmapDrawable(resources, createBitmap);
    }
}
