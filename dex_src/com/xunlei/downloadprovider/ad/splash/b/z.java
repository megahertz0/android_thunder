package com.xunlei.downloadprovider.ad.splash.b;

import android.view.ViewGroup;
import com.mediav.ads.sdk.adcore.Mvad;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.splash.c.a;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.xllib.R;

// compiled from: SplashQiHuAd.java
public final class z extends a {
    public z(int i, BaseActivity baseActivity, String str, ViewGroup viewGroup, l lVar, d dVar) {
        super(i, baseActivity, str, viewGroup, lVar, dVar);
    }

    public final void b() {
        super.b();
        Mvad.showSplashAd(this.c, this.a, this.b, new aa(this), Boolean.valueOf(true), Boolean.valueOf(false));
        c();
        a.a("adv_launch_360_request");
    }

    public final void b(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        a.a("ad_qihu360", "360", this.e, String.valueOf(d()), "360", "fullscreen");
        a(com.xunlei.downloadprovider.ad.common.d.d.a(Integer.parseInt("1115"), R.styleable.AppCompatTheme_radioButtonStyle));
        f();
    }

    protected final void d(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
    }

    protected final void e(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
    }

    public final void a() {
        a.a("adv_launch_360_fail", new StringBuilder("timeout_").append(this.g.a).toString());
    }
}
