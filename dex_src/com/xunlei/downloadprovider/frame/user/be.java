package com.xunlei.downloadprovider.frame.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.a;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.util.ab;

// compiled from: UserCenterFragment.java
final class be implements a {
    final /* synthetic */ UserCenterFragment a;

    be(UserCenterFragment userCenterFragment) {
        this.a = userCenterFragment;
    }

    public final void onLoadingCancelled(String str, View view) {
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        UserCenterFragment.f(this.a).setBackgroundResource(R.drawable.user_center_default_avatar);
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        Bitmap decodeResource;
        ImageView imageView = (ImageView) view;
        if (UserCenterFragment.d(this.a).f()) {
            decodeResource = BitmapFactory.decodeResource(UserCenterFragment.g(this.a).getResources(), R.drawable.user_center_avatar_album_vip);
        } else {
            decodeResource = BitmapFactory.decodeResource(UserCenterFragment.h(this.a).getResources(), R.drawable.user_center_avatar_album_no_vip);
        }
        imageView.setImageDrawable(ab.a(decodeResource, bitmap, UserCenterFragment.i(this.a).getResources()));
    }
}
