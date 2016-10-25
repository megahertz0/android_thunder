package com.xunlei.downloadprovider.ad.splash.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.umeng.a;

// compiled from: SplashThirdAdView.java
public final class j extends b {
    private Context l;
    private ViewGroup m;
    private boolean n;
    private View o;
    private String p;

    public j(Context context) {
        super(context);
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = null;
        this.p = a.d;
        this.l = context;
        this.o = LayoutInflater.from(this.l).inflate(2130968827, this);
        a();
    }

    protected final void a() {
        super.a();
        this.m = (ViewGroup) this.o.findViewById(2131756303);
        this.g = (CountDownCircleProgressBar) this.o.findViewById(2131756156);
        setAdStyle("fullscreen");
        k();
        this.g.setOnClickListener(new k(this));
    }

    public final ViewGroup getThirdAdContainer() {
        return this.m;
    }

    private void k() {
        this.g.setProgress(0);
    }

    public final void a(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
        k();
        setCountDown$2566ab5(5000);
        h();
        b();
    }
}
