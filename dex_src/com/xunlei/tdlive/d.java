package com.xunlei.tdlive;

import android.graphics.Rect;
import com.xunlei.tdlive.control.CropImageView.b;

// compiled from: CropImageActivity.java
class d implements b {
    final /* synthetic */ CropImageActivity a;

    d(CropImageActivity cropImageActivity) {
        this.a = cropImageActivity;
    }

    public void a(Rect rect) {
        this.a.findViewById(R.id.crop_rotate).setY(((float) rect.bottom) + com.xunlei.tdlive.util.d.a(this.a, 20.0f));
    }
}
