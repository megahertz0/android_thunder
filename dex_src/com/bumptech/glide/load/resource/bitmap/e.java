package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.widget.AutoScrollHelper;
import com.bumptech.glide.load.engine.a.c;

// compiled from: CenterCrop.java
public final class e extends d {
    public e(c cVar) {
        super(cVar);
    }

    protected final Bitmap a(c cVar, Bitmap bitmap, int i, int i2) {
        Config config;
        float f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
        if (bitmap.getConfig() != null) {
            config = bitmap.getConfig();
        } else {
            config = Config.ARGB_8888;
        }
        Bitmap a = cVar.a(i, i2, config);
        if (bitmap == null) {
            bitmap = null;
        } else if (!(bitmap.getWidth() == i && bitmap.getHeight() == i2)) {
            float height;
            float f2;
            Bitmap createBitmap;
            Matrix matrix = new Matrix();
            if (bitmap.getWidth() * i2 > bitmap.getHeight() * i) {
                height = ((float) i2) / ((float) bitmap.getHeight());
                f = (((float) i) - (((float) bitmap.getWidth()) * height)) * 0.5f;
                f2 = 0.0f;
            } else {
                height = ((float) i) / ((float) bitmap.getWidth());
                f2 = (((float) i2) - (((float) bitmap.getHeight()) * height)) * 0.5f;
            }
            matrix.setScale(height, height);
            matrix.postTranslate((float) ((int) (f + 0.5f)), (float) ((int) (f2 + 0.5f)));
            if (a == null) {
                createBitmap = Bitmap.createBitmap(i, i2, t.a(bitmap));
            } else {
                createBitmap = a;
            }
            t.a(bitmap, createBitmap);
            new Canvas(createBitmap).drawBitmap(bitmap, matrix, new Paint(6));
            bitmap = createBitmap;
        }
        if (!(a == null || a == bitmap || cVar.a(a))) {
            a.recycle();
        }
        return bitmap;
    }

    public final String a() {
        return "CenterCrop.com.bumptech.glide.load.resource.bitmap";
    }
}
