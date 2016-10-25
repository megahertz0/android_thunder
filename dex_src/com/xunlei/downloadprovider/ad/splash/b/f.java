package com.xunlei.downloadprovider.ad.splash.b;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.xunlei.downloadprovider.ad.recommend.a.b;
import com.xunlei.downloadprovider.ad.splash.b.a.a;

// compiled from: SplashAd.java
final class f implements a {
    final /* synthetic */ a a;
    final /* synthetic */ com.xunlei.downloadprovider.ad.splash.a.a b;
    final /* synthetic */ String c;
    final /* synthetic */ a d;

    f(a aVar, a aVar2, com.xunlei.downloadprovider.ad.splash.a.a aVar3, String str) {
        this.d = aVar;
        this.a = aVar2;
        this.b = aVar3;
        this.c = str;
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        if (!this.d.g.e) {
            this.a.a(b.a.a);
        }
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        if (!this.d.g.e) {
            if (bitmap == null) {
                this.a.a(b.a.a);
            } else if (this.b == null || !this.c.equals(this.b.j())) {
                this.a.a(b.a.a);
            } else {
                this.b.a(bitmap);
                this.a.a(bitmap);
            }
        }
    }

    public final void onLoadingCancelled(String str, View view) {
        if (!this.d.g.e) {
            this.a.a(b.a.a);
        }
    }
}
