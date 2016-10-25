package com.xunlei.downloadprovider.ad.recommend.c;

import android.view.View;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.downloadprovider.download.tasklist.list.a.o;
import com.xunlei.downloadprovider.download.tasklist.list.e.b.e;

// compiled from: RecommendGDTAdExposuredReporter.java
public final class c implements o {
    public int a;
    public Object b;
    public int c;
    private View d;

    public final int a() {
        return this.a;
    }

    public final boolean b() {
        return false;
    }

    public final void c() {
        new StringBuilder("RecommendGDTAdExposuredReporter excute pageIndex: ").append(this.a);
        if (this.b instanceof e) {
            ((NativeADDataRef) ((e) this.b).a()).onExposured(this.d);
        }
    }
}
