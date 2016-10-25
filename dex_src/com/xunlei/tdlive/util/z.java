package com.xunlei.tdlive.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.c;

// compiled from: UmengShareHelper.java
final class z extends c<View> {
    final /* synthetic */ String a;
    final /* synthetic */ Activity b;
    final /* synthetic */ SHARE_MEDIA c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;

    z(String str, Activity activity, SHARE_MEDIA share_media, String str2, String str3, String str4) {
        this.a = str;
        this.b = activity;
        this.c = share_media;
        this.d = str2;
        this.e = str3;
        this.f = str4;
    }

    public final void a(View view, String str, Bitmap bitmap, a aVar) {
        UMImage uMImage;
        if (bitmap != null) {
            bitmap = Bitmap.createScaledBitmap(bitmap, XLPayErrorCode.XLP_BD_PAYING, XLPayErrorCode.XLP_BD_PAYING, true);
        }
        if (bitmap == null || this.a == null || this.a.length() <= 0) {
            uMImage = new UMImage(this.b, R.drawable.xllive_logo);
        } else {
            uMImage = new UMImage(this.b, bitmap);
        }
        ShareAction shareAction = new ShareAction(this.b);
        shareAction.setPlatform(this.c).setCallback(y.a()).withMedia(uMImage);
        if (this.c == SHARE_MEDIA.WEIXIN_CIRCLE) {
            shareAction.withTitle(this.d + " " + this.e);
            shareAction.withText(" ");
        } else {
            shareAction.withTitle(this.d);
            shareAction.withText(this.e);
        }
        shareAction.withTargetUrl(this.f);
        shareAction.share();
    }

    public final void a(View view, String str, Drawable drawable) {
        a(view, str, null, null);
    }
}
