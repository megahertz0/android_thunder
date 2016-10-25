package com.xunlei.downloadprovider.ad.splash.b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.alipay.sdk.app.statistic.c;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.splash.view.b;
import com.xunlei.downloadprovider.ad.splash.view.e;
import com.xunlei.downloadprovider.ad.splash.view.j;
import com.xunlei.downloadprovider.ad.splash.view.l;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: SplashAd.java
public abstract class a implements com.xunlei.downloadprovider.ad.common.d.a, l {
    protected final BaseActivity a;
    protected final String b;
    protected final ViewGroup c;
    protected a d;
    protected int e;
    protected long f;
    protected d g;
    protected boolean h;
    protected boolean i;
    protected boolean j;
    protected boolean k;
    protected b l;
    private final l m;

    // compiled from: SplashAd.java
    public static interface a {
        void a(Bitmap bitmap);

        void a(com.xunlei.downloadprovider.ad.recommend.a.b.a aVar);
    }

    protected abstract void d(com.xunlei.downloadprovider.ad.splash.a.a aVar);

    protected abstract void e(com.xunlei.downloadprovider.ad.splash.a.a aVar);

    public a(int i, BaseActivity baseActivity, String str, ViewGroup viewGroup, l lVar, d dVar) {
        this.d = null;
        this.f = 0;
        this.g = null;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = null;
        this.e = i;
        this.a = baseActivity;
        this.b = str;
        this.c = viewGroup;
        this.m = lVar;
        this.g = dVar;
    }

    public void b() {
        this.g.a(this);
    }

    public final void a(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        if (!this.g.e) {
            b(aVar);
        }
    }

    protected final void a(com.xunlei.downloadprovider.ad.recommend.a.b.a aVar) {
        if (!this.g.e) {
            if (this.d != null) {
                this.d.b();
            } else {
                if (aVar == null) {
                    aVar = com.xunlei.downloadprovider.ad.recommend.a.b.a.a;
                }
                a(aVar.e, aVar.f);
            }
            this.g.c.remove(this);
        }
    }

    protected void b(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        b jVar;
        String c = aVar.c();
        if (c.equals(c.e)) {
            jVar = new j(this.a);
        } else if (c.equals("fullscreen")) {
            jVar = new e(this.a);
        } else {
            jVar = new l(this.a);
        }
        this.l = jVar;
        View view = this.l;
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.c.removeAllViews();
        this.c.addView(view, layoutParams);
        c(aVar);
        this.l.a(aVar);
    }

    protected void c(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        this.l.setOnAdClickListener(new b(this, aVar));
        this.l.setOnAdDismissListener(new c(this));
        this.l.setOnAdShowListener(new d(this, aVar));
        this.l.setOnSkipBtnClickListener(new e(this, aVar));
    }

    public final void a(a aVar) {
        this.d = aVar;
    }

    protected final void a(com.xunlei.downloadprovider.ad.splash.a.a aVar, a aVar2) {
        String j = aVar.j();
        if (TextUtils.isEmpty(j)) {
            aVar2.a(com.xunlei.downloadprovider.ad.recommend.a.b.a.a);
        } else {
            com.nostra13.universalimageloader.core.d.a().a(j, com.xunlei.downloadprovider.download.util.l.a().c, new f(this, aVar2, aVar, j));
        }
    }

    protected final void c() {
        this.f = System.currentTimeMillis();
    }

    protected final long d() {
        return System.currentTimeMillis() - this.f;
    }

    protected final void a(String str) {
        a(Integer.parseInt("1115"), str, 1);
    }

    protected final void b(String str) {
        a(Integer.parseInt("1115"), str, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    private void a(int i, String str, int i2) {
        com.xunlei.downloadprovider.ad.common.d.a aVar = new com.xunlei.downloadprovider.ad.common.d.a.a();
        aVar.b = str;
        aVar.c = i;
        aVar.a = i2;
        com.xunlei.downloadprovider.j.a.a().e().a(new com.xunlei.downloadprovider.ad.common.d.c(aVar, new g(this), new h(this)));
    }

    public final void e() {
        if (!this.h) {
            this.m.e();
            this.h = true;
        }
    }

    public final void a(int i, String str) {
        if (!this.i) {
            this.m.a(i, str);
            this.i = true;
        }
    }

    public final void f() {
        if (!this.j) {
            this.m.f();
            this.j = true;
        }
    }

    public final void g() {
        if (!this.k) {
            if (this.l != null) {
                this.l.e();
            }
            this.m.g();
            this.k = true;
        }
    }

    public final void h() {
        this.m.h();
    }
}
