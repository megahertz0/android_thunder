package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import android.os.Handler;
import com.qq.e.ads.cfg.MultiProcessFlag;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.ui.n;

// compiled from: LoadADClient.java
public class c {
    public static final String a;
    private static c e;
    public Context b;
    public a c;
    public n d;

    static {
        a = c.class.getSimpleName();
    }

    private c(Context context) {
        this.b = context;
        this.c = new a(this);
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (e == null) {
                e = new c(context);
                MultiProcessFlag.setMultiProcess(true);
            }
            cVar = e;
        }
        return cVar;
    }

    public final void a() {
        if (this.d != null) {
            new Handler().post(new f(this));
        }
    }

    public final void a(a aVar, ADItemView aDItemView, n nVar) {
        this.d = nVar;
        new StringBuilder("refreshNavAD key: ").append(aDItemView.getViewPositionKey()).append(" layout_type: ").append(aDItemView.getADType().name());
        if (aDItemView != null) {
            com.xunlei.downloadprovider.ad.common.a a = this.c.a(aVar.d);
            if (a != null) {
                new StringBuilder("use cache key: ").append(aDItemView.getViewPositionKey()).append(" layout: ").append(aDItemView.getADType().name());
                aDItemView.a(a);
            }
        }
    }
}
