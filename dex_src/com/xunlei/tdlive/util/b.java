package com.xunlei.tdlive.util;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.d;

// compiled from: BitmapDisplay.java
class b extends d {
    final /* synthetic */ com.xunlei.tdlive.util.a.b a;
    final /* synthetic */ View b;
    final /* synthetic */ a c;
    final /* synthetic */ a d;

    b(a aVar, com.xunlei.tdlive.util.a.b bVar, View view, a aVar2) {
        this.d = aVar;
        this.a = bVar;
        this.b = view;
        this.c = aVar2;
        super(null);
    }

    public void onLoadingStarted(String str, View view) {
        try {
            this.a.a(this.b, str, this.c);
            this.a.b(this.b, str, this.c);
        } catch (Exception e) {
        }
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
        try {
            this.a.a(this.b, str, this.c == null ? null : this.c.d());
        } catch (Exception e) {
        }
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        try {
            this.a.a(this.b, str, bitmap, this.c);
        } catch (Exception e) {
        }
    }

    public void onLoadingCancelled(String str, View view) {
        onLoadingFailed(str, view, new FailReason(FailType.UNKNOWN, null));
    }
}
