package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import com.baidu.mobad.feeds.NativeResponse;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.ad.common.d.a.a;
import com.xunlei.downloadprovider.ad.common.d.d;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

// compiled from: CommendBaiduShowReporter.java
public final class b implements o {
    private int a;
    private NativeResponse b;
    private View c;
    private String d;

    public b(View view, NativeResponse nativeResponse, int i, String str) {
        this.a = i;
        this.b = nativeResponse;
        this.c = view;
        this.d = str;
    }

    public final int a() {
        return this.a;
    }

    public final boolean b() {
        return !this.d.equals(c.f);
    }

    public final void c() {
        a aVar = new a();
        n.a();
        aVar.b = d.a(Integer.valueOf(n.a(this.a)).intValue(), R.styleable.AppCompatTheme_buttonStyleSmall);
        n.a();
        aVar.c = Integer.valueOf(n.a(this.a)).intValue();
        aVar.a = 1;
        com.xunlei.downloadprovider.j.a.a().e().a(new com.xunlei.downloadprovider.ad.common.d.c(aVar, new c(this), new d(this)));
        this.b.recordImpression(this.c);
        ThunderReporter.a.a(h.a(this.a), "baidu", "baidu", this.d, BuildConfig.VERSION_NAME);
    }
}
