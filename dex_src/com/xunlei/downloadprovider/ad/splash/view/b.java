package com.xunlei.downloadprovider.ad.splash.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import com.xunlei.downloadprovider.ad.splash.view.a.a;
import com.xunlei.downloadprovider.commonview.dialog.d;

// compiled from: SplashAdView.java
public abstract class b extends FrameLayout implements a {
    protected Context a;
    protected OnClickListener b;
    protected OnClickListener c;
    protected com.xunlei.downloadprovider.ad.splash.view.a.b d;
    protected a e;
    protected long f;
    protected CountDownCircleProgressBar g;
    protected ValueAnimator h;
    protected String i;
    protected boolean j;
    protected d k;
    private boolean l;

    public b(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.l = true;
        this.f = 3000;
        this.g = null;
        this.h = null;
        this.i = com.umeng.a.d;
        this.j = false;
        this.k = null;
        this.a = context;
    }

    protected void a() {
        this.h = ValueAnimator.ofInt(new int[]{0, 100});
    }

    public final void b() {
        this.l = false;
        if (this.d != null) {
            this.d.a();
        }
    }

    public final void c() {
        if (!(this.l || this.e == null)) {
            this.e.a();
        }
        this.l = true;
    }

    public void setOnAdClickListener(OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public void setOnSkipBtnClickListener(OnClickListener onClickListener) {
        this.c = onClickListener;
    }

    public void setOnAdDismissListener(a aVar) {
        this.e = aVar;
    }

    public void setOnAdShowListener(com.xunlei.downloadprovider.ad.splash.view.a.b bVar) {
        this.d = bVar;
    }

    public String getAdStyle() {
        return this.i;
    }

    protected void setAdStyle(String str) {
        this.i = str;
    }

    public final void d() {
        this.j = true;
    }

    public final void e() {
        i();
        g();
        this.k = null;
    }

    protected final void a(DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        if (this.k == null) {
            this.k = new d(this.a);
            this.k.setTitle((CharSequence) "\u6e29\u99a8\u63d0\u793a");
            this.k.b((CharSequence) "\u5f53\u524d\u4e3a\u79fb\u52a8\u7f51\u7edc\uff0c\u5f00\u59cb\u4e0b\u8f7d/\u5b89\u88c5\u5e94\u7528\uff1f");
            this.k.d("\u786e\u8ba4");
            this.k.c("\u53d6\u6d88");
        }
        this.k.b(onClickListener);
        this.k.a(onClickListener2);
    }

    protected final void f() {
        if (this.k != null) {
            this.k.show();
        }
    }

    protected final void g() {
        if (this.k != null) {
            this.k.dismiss();
        }
    }

    public final void setCountDown$2566ab5(long j) {
        this.h.removeAllUpdateListeners();
        this.h.addUpdateListener(new c(this));
        this.h.setRepeatCount(0);
        this.h.setDuration(j);
        this.h.setInterpolator(new LinearInterpolator());
        this.h.removeAllListeners();
        this.h.addListener(new d(this));
    }

    public final void h() {
        if (this.h != null) {
            this.h.start();
        }
    }

    public final void i() {
        if (this.h != null) {
            this.h.cancel();
        }
    }

    public final void j() {
        c();
    }
}
