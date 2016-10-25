package com.xunlei.downloadprovider.pushmessage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.a;

// compiled from: PushResultDispatcher.java
final class e implements a {
    final /* synthetic */ Context a;
    final /* synthetic */ com.xunlei.downloadprovider.pushmessage.a.a b;

    e(Context context, com.xunlei.downloadprovider.pushmessage.a.a aVar) {
        this.a = context;
        this.b = aVar;
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        g.a(this.a, this.b, null);
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        g.a(this.a, this.b, bitmap);
    }

    public final void onLoadingCancelled(String str, View view) {
    }
}
