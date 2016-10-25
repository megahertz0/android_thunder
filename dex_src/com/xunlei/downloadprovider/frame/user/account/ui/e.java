package com.xunlei.downloadprovider.frame.user.account.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.xunlei.downloadprovider.util.ab;

// compiled from: UserAccountInfoActivityNew.java
final class e implements a {
    final /* synthetic */ boolean a;
    final /* synthetic */ UserAccountInfoActivityNew b;

    e(UserAccountInfoActivityNew userAccountInfoActivityNew, boolean z) {
        this.b = userAccountInfoActivityNew;
        this.a = z;
    }

    public final void onLoadingCancelled(String str, View view) {
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        this.b.d.getAccountItemTipPic().setBackgroundResource(2130838392);
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        ((ImageView) view).setImageDrawable(ab.a(BitmapFactory.decodeResource(this.b.getResources(), 2130839589), bitmap, this.b.getResources()));
    }
}
