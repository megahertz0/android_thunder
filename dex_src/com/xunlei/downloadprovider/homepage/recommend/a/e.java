package com.xunlei.downloadprovider.homepage.recommend.a;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.a;

// compiled from: ShortTimeVideoListAdapter.java
final class e implements a {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public final void onLoadingCancelled(String str, View view) {
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        ImageView imageView = (ImageView) view;
        if (str.equals(imageView.getTag())) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
