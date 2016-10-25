package com.xunlei.tdlive.play.a;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.support.v4.widget.AutoScrollHelper;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.c;

// compiled from: ReplayActivityPresenter.java
class y extends c<ImageView> {
    final /* synthetic */ v a;

    y(v vVar) {
        this.a = vVar;
    }

    public void a(ImageView imageView, String str, Bitmap bitmap, a aVar) {
        try {
            Point point = new Point();
            this.a.a.getWindowManager().getDefaultDisplay().getSize(point);
            float f = (float) point.x;
            float f2 = (float) point.y;
            float height = (float) bitmap.getHeight();
            float width = ((float) bitmap.getWidth()) * (f2 / height);
            Matrix matrix = new Matrix();
            matrix.postScale(f2 / height, f2 / height);
            matrix.postTranslate((f - width) / 2.0f, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            imageView.setScaleType(ScaleType.MATRIX);
            imageView.setImageMatrix(matrix);
            super.a(imageView, str, bitmap, aVar);
        } catch (Exception e) {
        }
    }
}
