package com.xunlei.downloadprovider.homepage.relax;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.d.c;
import com.xunlei.tdlive.sdk.IHost;

// compiled from: RelaxPicBrowseActivity.java
final class h extends c {
    final /* synthetic */ RelaxPicBrowseActivity a;

    h(RelaxPicBrowseActivity relaxPicBrowseActivity) {
        this.a = relaxPicBrowseActivity;
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        if (this.a.k != null) {
            this.a.k.removeMessages(IHost.HOST_NOFITY_REFRESH_LIST);
            this.a.k.sendEmptyMessage(IHost.HOST_NOFITY_REFRESH_LIST);
            this.a.k = null;
        }
    }
}
