package com.xunlei.tdlive;

import android.graphics.Bitmap;
import android.view.View;
import com.xunlei.tdlive.protocol.XLLiveGetSplashImageRequest.GetSplashImageResp;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.c;

// compiled from: SplashActivity.java
class em extends c<View> {
    final /* synthetic */ GetSplashImageResp a;
    final /* synthetic */ SplashActivity b;

    em(SplashActivity splashActivity, GetSplashImageResp getSplashImageResp) {
        this.b = splashActivity;
        this.a = getSplashImageResp;
    }

    public void a(View view, String str, Bitmap bitmap, a aVar) {
        this.b.putString("splash_id", this.a.data.id);
        this.b.putString("splash_md5", this.a.data.md5);
        this.b.putString("splash_image", this.a.data.image);
        this.b.putString("splash_beg_date", this.a.data.begin_date);
        this.b.putString("splash_end_date", this.a.data.end_date);
        this.b.putInt("splash_display_count", this.a.data.display_count);
    }
}
