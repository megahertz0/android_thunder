package com.xunlei.tdlive;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.c;
import org.android.spdy.SpdyProtocol;

// compiled from: SplashActivity.java
class el extends c<View> {
    final /* synthetic */ SplashActivity a;

    el(SplashActivity splashActivity) {
        this.a = splashActivity;
    }

    public void a(View view, String str, Bitmap bitmap, a aVar) {
        this.a.b.setAnimation(AnimationUtils.loadAnimation(this.a.getApplicationContext(), 17432577));
        this.a.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.c.setImageBitmap(bitmap);
        this.a.c.setAnimation(AnimationUtils.loadAnimation(this.a.getApplicationContext(), 17432576));
        this.a.c.setVisibility(0);
    }
}
