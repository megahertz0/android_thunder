package com.xunlei.downloadprovider.ad.splash.b;

import android.view.ViewGroup;
import com.baidu.mobad.feeds.BaiduNative;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.splash.c.a;
import com.xunlei.downloadprovider.app.BaseActivity;

// compiled from: SplashBaiDuAd.java
public final class m extends a {
    public m(int i, BaseActivity baseActivity, String str, ViewGroup viewGroup, l lVar, d dVar) {
        super(i, baseActivity, str, viewGroup, lVar, dVar);
    }

    public final void b() {
        super.b();
        new BaiduNative(this.a.getApplicationContext(), this.b, new n(this)).makeRequest();
        c();
        a.a("adv_launch_baidu_request");
    }

    protected final void c(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        super.c(aVar);
        this.l.d();
    }

    protected final void d(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
    }

    protected final void e(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
    }

    public final void a() {
        a.a("adv_launch_baidu_fail", new StringBuilder("timeout_").append(this.g.a).toString());
    }
}
