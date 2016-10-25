package com.xunlei.tdlive;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.c;

// compiled from: WebBrowserActivity.java
class fx extends c<View> {
    final /* synthetic */ fw a;

    fx(fw fwVar) {
        this.a = fwVar;
    }

    public void a(View view, String str, Bitmap bitmap, a aVar) {
        if (!this.a.a.isDestroyed()) {
            if (bitmap != null) {
                this.a.a.setRightDrawable(new BitmapDrawable(this.a.a.getResources(), bitmap));
            } else {
                this.a.a.setRightDrawable(this.a.a.getResources().getDrawable(R.drawable.xllive_more));
            }
            this.a.a.setRightVisible(true);
        }
    }
}
