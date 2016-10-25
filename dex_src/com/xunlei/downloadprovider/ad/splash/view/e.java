package com.xunlei.downloadprovider.ad.splash.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.downloadprovider.ad.splash.a.a;
import com.xunlei.downloadprovider.ad.splash.a.c;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: SplashFullAdView.java
public final class e extends b {
    private ImageView l;
    private ImageView m;
    private View n;
    private a o;

    public e(Context context) {
        super(context);
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.n = LayoutInflater.from(this.a).inflate(2130968825, this);
        a();
    }

    protected final void a() {
        super.a();
        this.l = (ImageView) this.n.findViewById(2131756296);
        this.m = (ImageView) this.n.findViewById(2131756297);
        this.g = (CountDownCircleProgressBar) this.n.findViewById(2131756156);
        k();
        setAdStyle("fullscreen");
        this.g.setOnClickListener(new f(this));
        setOnClickListener(new g(this));
    }

    public final void a(a aVar) {
        this.o = aVar;
        k();
        this.f = Math.max(TabsImpl.SYNC_TIME_OUT, this.o.b());
        setCountDown$2566ab5(this.f);
        setImageBitmap(this.o.a());
        if (aVar instanceof c) {
            setAdSourceIconIv(2130839277);
        }
        h();
        b();
    }

    public final void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            this.l.setImageBitmap(bitmap);
        } else {
            this.l.setImageResource(R.color.transparent);
        }
    }

    public final void setImageRes(int i) {
        this.l.setImageResource(i);
    }

    public final void setAdSourceIconIv(int i) {
        this.m.setImageResource(i);
        this.m.setVisibility(0);
    }

    private void k() {
        this.g.setProgress(0);
        setImageRes(R.color.transparent);
        this.m.setVisibility(XZBDevice.Wait);
    }
}
