package com.xunlei.downloadprovider.homepage.choiceness;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

// compiled from: ChoicenessUtils.java
final class c extends com.nostra13.universalimageloader.core.d.c {
    final /* synthetic */ ImageView a;
    final /* synthetic */ int b;

    c(ImageView imageView, int i) {
        this.a = imageView;
        this.b = i;
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        this.a.setTag(this.b, str);
    }
}
