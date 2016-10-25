package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import com.bumptech.glide.load.engine.a.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: FitCenter.java
public final class l extends d {
    public l(c cVar) {
        super(cVar);
    }

    public final String a() {
        return "FitCenter.com.bumptech.glide.load.resource.bitmap";
    }

    protected final Bitmap a(c cVar, Bitmap bitmap, int i, int i2) {
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        float min = Math.min(((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
        int width = (int) (((float) bitmap.getWidth()) * min);
        int height = (int) (((float) bitmap.getHeight()) * min);
        if (bitmap.getWidth() == width && bitmap.getHeight() == height) {
            return bitmap;
        }
        Config a = t.a(bitmap);
        Bitmap a2 = cVar.a(width, height, a);
        if (a2 == null) {
            a2 = Bitmap.createBitmap(width, height, a);
        }
        t.a(bitmap, a2);
        if (Log.isLoggable("TransformationUtils", XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            new StringBuilder("request: ").append(i).append("x").append(i2);
            new StringBuilder("toFit:   ").append(bitmap.getWidth()).append("x").append(bitmap.getHeight());
            new StringBuilder("toReuse: ").append(a2.getWidth()).append("x").append(a2.getHeight());
        }
        Canvas canvas = new Canvas(a2);
        Matrix matrix = new Matrix();
        matrix.setScale(min, min);
        canvas.drawBitmap(bitmap, matrix, new Paint(6));
        return a2;
    }
}
