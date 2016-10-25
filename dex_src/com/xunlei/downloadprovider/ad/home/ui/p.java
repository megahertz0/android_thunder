package com.xunlei.downloadprovider.ad.home.ui;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.d.c;

// compiled from: ADShortVideoItemView.java
final class p extends c {
    final /* synthetic */ String a;
    final /* synthetic */ ADShortVideoItemView b;

    p(ADShortVideoItemView aDShortVideoItemView, String str) {
        this.b = aDShortVideoItemView;
        this.a = str;
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        a aVar = (a) this.b.getTag();
        if (!(!this.a.equals(this.b.b) || aVar == null || aVar.f == null)) {
            aVar.f.setImageBitmap(bitmap);
        }
        com.xunlei.downloadprovider.ad.home.a.c.a(this.b.getContext()).c.b.remove(this.a);
    }
}
