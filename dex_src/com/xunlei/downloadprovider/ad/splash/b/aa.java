package com.xunlei.downloadprovider.ad.splash.b;

import com.mediav.ads.sdk.interfaces.IMvAdEventListener;
import com.xunlei.downloadprovider.ad.common.d.d;
import com.xunlei.downloadprovider.ad.recommend.a.b.a;
import com.xunlei.xllib.R;

// compiled from: SplashQiHuAd.java
final class aa implements IMvAdEventListener {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    public final void onAdviewGotAdSucceed() {
        if (!this.a.g.e) {
            this.a.e();
        }
    }

    public final void onAdviewGotAdFail() {
        if (!this.a.g.e) {
            this.a.a(a.a);
            com.xunlei.downloadprovider.ad.splash.c.a.a("adv_launch_360_fail", "-1");
        }
    }

    public final void onAdviewRendered() {
        if (!this.a.g.e) {
            this.a.a(null);
        }
    }

    public final void onAdviewIntoLandpage() {
    }

    public final void onAdviewDismissedLandpage() {
    }

    public final void onAdviewClicked() {
        com.xunlei.downloadprovider.ad.splash.c.a.a("ad_qihu360", "360", this.a.e, "360", "fullscreen");
        this.a.b(d.a(Integer.parseInt("1115"), R.styleable.AppCompatTheme_radioButtonStyle));
        this.a.h();
    }

    public final void onAdviewClosed() {
        if (!this.a.g.e) {
            this.a.g();
        }
    }

    public final void onAdviewDestroyed() {
        if (!this.a.g.e) {
        }
    }
}
