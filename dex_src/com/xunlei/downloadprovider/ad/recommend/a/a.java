package com.xunlei.downloadprovider.ad.recommend.a;

import android.content.Context;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: BaseRecommendAdExecutor.java
public abstract class a extends com.xunlei.downloadprovider.ad.common.b.a {
    protected static final String b;
    protected Context c;
    protected final int d;
    protected final String e;
    protected final com.xunlei.downloadprovider.ad.recommend.a.i.a f;
    protected String g;

    static {
        b = a.class.getSimpleName();
    }

    public a(Context context, int i, String str, com.xunlei.downloadprovider.ad.recommend.a.i.a aVar, String str2) {
        this.c = null;
        this.g = BuildConfig.VERSION_NAME;
        this.c = context;
        this.d = i;
        this.e = str;
        this.f = aVar;
        this.g = str2;
    }
}
