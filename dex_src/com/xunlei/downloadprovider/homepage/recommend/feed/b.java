package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.a;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.util.ab;

// compiled from: ChannelFeedVideoItemView.java
public final class b implements a {
    final /* synthetic */ ImageView a;
    final /* synthetic */ a b;

    public b(a aVar, ImageView imageView) {
        this.b = aVar;
        this.a = imageView;
    }

    public final void onLoadingCancelled(String str, View view) {
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        this.a.setBackgroundResource(R.drawable.feedflow_icon_default);
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        ((ImageView) view).setImageDrawable(ab.a(BitmapFactory.decodeResource(this.b.getContext().getResources(), R.drawable.user_center_avatar_album_trans), bitmap, this.b.getContext().getResources()));
    }
}
