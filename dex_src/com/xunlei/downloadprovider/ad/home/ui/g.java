package com.xunlei.downloadprovider.ad.home.ui;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.d.c;

// compiled from: ADImageItemview.java
final class g extends c {
    final /* synthetic */ String a;
    final /* synthetic */ ADImageItemview b;

    g(ADImageItemview aDImageItemview, String str) {
        this.b = aDImageItemview;
        this.a = str;
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        a aVar = (a) this.b.getTag();
        if (!(!this.a.equals(this.b.b) || aVar == null || aVar.c == null)) {
            aVar.c.setImageBitmap(bitmap);
        }
        com.xunlei.downloadprovider.ad.home.a.c.a(this.b.getContext()).c.b.remove(this.a);
    }
}
