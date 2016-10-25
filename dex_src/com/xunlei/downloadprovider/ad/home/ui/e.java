package com.xunlei.downloadprovider.ad.home.ui;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.d.c;

// compiled from: ADGDTVideoItem.java
final class e extends c {
    final /* synthetic */ String a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ a c;

    e(a aVar, String str, ImageView imageView) {
        this.c = aVar;
        this.a = str;
        this.b = imageView;
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        if (this.a.equals(this.c.j)) {
            this.b.setImageBitmap(bitmap);
        }
        com.xunlei.downloadprovider.ad.home.a.c.a(this.c.getContext().getApplicationContext()).c.b.remove(this.a);
    }
}
