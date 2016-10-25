package com.xunlei.tdlive.usercenter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.b;
import org.android.spdy.SpdyProtocol;

// compiled from: UserInfoTop.java
class u extends b<ImageView> {
    final /* synthetic */ UserInfoTop a;

    u(UserInfoTop userInfoTop) {
        this.a = userInfoTop;
    }

    public void a(ImageView imageView, String str, Bitmap bitmap, a aVar) {
        imageView.setVisibility(0);
        imageView.setImageBitmap(bitmap);
    }

    public void a(ImageView imageView, String str, Drawable drawable) {
        imageView.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
