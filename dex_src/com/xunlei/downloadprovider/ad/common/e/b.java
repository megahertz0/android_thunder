package com.xunlei.downloadprovider.ad.common.e;

import android.content.Context;
import com.qq.e.ads.nativ.NativeAD;
import com.xunlei.downloadprovider.ad.common.c;
import com.xunlei.downloadprovider.ad.common.c.a;

// compiled from: GDTNavLoader.java
public final class b implements c {
    private Context a;
    private String b;
    private int c;

    public b(Context context, String str, int i) {
        this.b = str;
        this.a = context;
        this.c = i;
    }

    public final void a(a aVar, String str) {
        new NativeAD(this.a, "1104872693", this.b, new c(this, aVar, str)).loadAD(this.c);
    }
}
