package com.xunlei.downloadprovider.homepage.recommend.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.a;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.util.ab;

// compiled from: ShortTimeVideoListAdapter.java
final class c implements a {
    final /* synthetic */ TextView a;
    final /* synthetic */ a b;

    c(a aVar, TextView textView) {
        this.b = aVar;
        this.a = textView;
    }

    public final void onLoadingCancelled(String str, View view) {
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        ((ImageView) view).setBackgroundResource(R.drawable.feedflow_icon_default);
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        ImageView imageView = (ImageView) view;
        if (bitmap != null) {
            this.a.setVisibility(0);
            a aVar = this.b;
            imageView.setImageDrawable(ab.a(BitmapFactory.decodeResource(aVar.e.getResources(), R.drawable.user_center_avatar_album_trans), bitmap, aVar.e.getResources()));
            return;
        }
        imageView.setImageResource(R.drawable.feedflow_icon_default);
    }
}
