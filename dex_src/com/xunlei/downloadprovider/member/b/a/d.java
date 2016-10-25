package com.xunlei.downloadprovider.member.b.a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xunlei.downloadprovider.member.b.c;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: VipRenewalTip.java
public final class d implements c {
    long a;
    int b;
    String c;
    String d;
    public String e;
    public String f;
    public String g;
    String h;
    String i;
    String j;
    public int k;

    public d() {
        this.c = BuildConfig.VERSION_NAME;
        this.d = BuildConfig.VERSION_NAME;
        this.e = BuildConfig.VERSION_NAME;
        this.f = BuildConfig.VERSION_NAME;
        this.g = BuildConfig.VERSION_NAME;
        this.h = BuildConfig.VERSION_NAME;
        this.i = BuildConfig.VERSION_NAME;
        this.j = BuildConfig.VERSION_NAME;
        this.a = SystemClock.elapsedRealtime();
    }

    public final boolean a() {
        return this.b == 200 && !TextUtils.isEmpty(this.d);
    }

    public final String b() {
        return this.e;
    }

    public final String c() {
        return this.f;
    }

    public final String d() {
        return this.g;
    }

    public final int e() {
        return this.k;
    }
}
