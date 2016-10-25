package com.xunlei.tdlive;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.support.v4.widget.AutoScrollHelper;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.c;

// compiled from: LivePlayerActivity.java
class ah extends c<ImageView> {
    final /* synthetic */ LivePlayerActivity a;

    ah(LivePlayerActivity livePlayerActivity) {
        this.a = livePlayerActivity;
    }

    public void a(ImageView imageView, String str, Bitmap bitmap, a aVar) {
        try {
            Point point = new Point();
            this.a.getWindowManager().getDefaultDisplay().getSize(point);
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
