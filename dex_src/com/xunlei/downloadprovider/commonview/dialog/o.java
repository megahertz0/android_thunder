package com.xunlei.downloadprovider.commonview.dialog;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

// compiled from: XLImageButtonDialog.java
final class o implements OnGlobalLayoutListener {
    final /* synthetic */ ImageView a;
    final /* synthetic */ View b;
    final /* synthetic */ k c;

    o(k kVar, ImageView imageView, View view) {
        this.c = kVar;
        this.a = imageView;
        this.b = view;
    }

    public final void onGlobalLayout() {
        if (this.a.getVisibility() == 8) {
            LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
            layoutParams.width = this.b.getWidth();
            layoutParams.height = this.b.getHeight();
            this.a.setLayoutParams(layoutParams);
            this.a.setVisibility(0);
        }
    }
}
