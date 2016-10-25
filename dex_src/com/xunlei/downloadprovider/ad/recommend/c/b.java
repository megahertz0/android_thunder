package com.xunlei.downloadprovider.ad.recommend.c;

import android.view.View;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.download.tasklist.list.a.o;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: RecommendAdShowReporter.java
public class b implements o {
    private static final String d;
    public int a;
    public int b;
    public a c;
    private String e;
    private String f;
    private View g;
    private String h;

    static {
        d = b.class.getSimpleName();
    }

    public b(View view, a aVar, String str, String str2, String str3, int i, int i2) {
        this.c = null;
        this.g = null;
        this.h = null;
        this.g = view;
        this.c = aVar;
        this.f = str;
        this.e = str2;
        this.a = i;
        this.b = i2;
        this.h = str3;
    }

    public final int a() {
        return this.a;
    }

    public final boolean b() {
        return false;
    }

    public final void c() {
        new StringBuilder("RecommendShowReporter excute  position ").append(this.b).append(" pageIndex: ").append(this.a);
        int i = this.a;
        String valueOf = String.valueOf(this.b);
        String str = this.e;
        String str2 = this.f;
        String str3 = this.h;
        new StringBuilder("reportRecommendAdShow attr: adv_downloadin_show tabId: ").append(i).append(" adPosition: ").append(valueOf).append(" adType: ").append(str).append(" adId: ").append(str2).append(" material: ").append(str3);
        d.a(com.xunlei.downloadprovidercommon.a.a.a("android_advertise", "adv_downloadin_show").b("tabid", a.a(i)).b("adv_position", valueOf).b("ad_type", str).b("advid", str2).b("material", str3));
        this.c.a(this.g);
    }
}
