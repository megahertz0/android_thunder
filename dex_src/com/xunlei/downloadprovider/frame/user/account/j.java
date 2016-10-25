package com.xunlei.downloadprovider.frame.user.account;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.util.ab;

// compiled from: SetAccountPortraitHelper.java
final class j implements a {
    final /* synthetic */ ImageView a;
    final /* synthetic */ boolean b;
    final /* synthetic */ boolean c;
    final /* synthetic */ h d;

    j(h hVar, ImageView imageView, boolean z, boolean z2) {
        this.d = hVar;
        this.a = imageView;
        this.b = z;
        this.c = z2;
    }

    public final void onLoadingCancelled(String str, View view) {
    }

    public final void onLoadingStarted(String str, View view) {
    }

    public final void onLoadingFailed(String str, View view, FailReason failReason) {
        this.a.setBackgroundResource(2130838392);
    }

    public final void onLoadingComplete(String str, View view, Bitmap bitmap) {
        ((ImageView) view).setImageDrawable(ab.a(BitmapFactory.decodeResource(this.d.b.getResources(), 2130839589), bitmap, this.d.b.getResources()));
        if (this.c) {
            LoginHelper.a().a(bitmap, this.d.c);
        }
    }
}
