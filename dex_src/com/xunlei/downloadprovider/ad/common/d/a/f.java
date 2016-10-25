package com.xunlei.downloadprovider.ad.common.d.a;

import android.view.View;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.a;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SSPProxyModel.java
public final class f extends a {
    String a;
    String b;
    String c;
    String d;
    boolean e;
    float f;
    String g;
    String h;
    boolean i;
    String j;
    String k;
    String l;
    String m;
    String n;
    String o;

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String d() {
        return this.d;
    }

    public final boolean e() {
        return this.e;
    }

    public final String c() {
        return this.c;
    }

    public final float g() {
        return this.f;
    }

    public final String r() {
        return this.h;
    }

    public final String s() {
        return this.g;
    }

    public final String j() {
        return this.m;
    }

    public final boolean l() {
        return this.i;
    }

    public final String n() {
        return this.j;
    }

    public final String m() {
        return this.k;
    }

    public final String h() {
        return this.l;
    }

    public final String k() {
        return this.n;
    }

    public final String i() {
        return this.o;
    }

    public final AD_TYPE o() {
        return this.i ? AD_TYPE.SOURCE_SSP_DEFAULT_FLAG : AD_TYPE.SOURCE_SSP_FLAG;
    }

    public final void onClick(View view) {
        if (!this.e) {
            a(view.getContext());
        }
        super.a(SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void a(View view) {
        super.a(1);
    }
}
