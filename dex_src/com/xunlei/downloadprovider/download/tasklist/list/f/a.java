package com.xunlei.downloadprovider.download.tasklist.list.f;

import android.os.Handler;
import com.xunlei.downloadprovider.download.util.k;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: RedEnvelopeControl.java
public final class a {
    public static a i;
    public boolean a;
    public com.xunlei.downloadprovider.download.tasklist.a.a b;
    public e c;
    public Handler d;
    int e;
    int f;
    public String g;
    public String h;
    public k j;

    public a() {
        this.a = false;
        this.d = new Handler();
        this.g = BuildConfig.VERSION_NAME;
        this.h = BuildConfig.VERSION_NAME;
        this.j = new b(this, this.d);
    }
}
