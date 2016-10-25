package com.xunlei.downloadprovider.homepage.relax.c;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;

// compiled from: ImageLoadCtrl.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        a.a;
        if (this.a.b != null) {
            this.a.b.a();
        }
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        a.a;
        if (this.a.b != null) {
            if (bitmap != null) {
                bitmap.getWidth();
            }
            a.a(str);
            a.a;
            this.a.b.a(a.a(str));
        }
    }

    public final void onLoadingCancelled(String str, View view) {
        a.a;
    }
}
