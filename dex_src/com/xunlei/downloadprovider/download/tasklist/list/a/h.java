package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.view.View;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.ad.common.d.a.a;
import com.xunlei.downloadprovider.ad.common.d.d;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

// compiled from: CommendGDTShowReporter.java
public final class h implements o {
    private int a;
    private NativeADDataRef b;
    private View c;
    private String d;

    public h(View view, NativeADDataRef nativeADDataRef, int i, String str) {
        this.a = i;
        this.b = nativeADDataRef;
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
        aVar.b = d.a(Integer.valueOf(n.a(this.a)).intValue(), R.styleable.AppCompatTheme_buttonStyle);
        n.a();
        aVar.c = Integer.valueOf(n.a(this.a)).intValue();
        aVar.a = 1;
        com.xunlei.downloadprovider.j.a.a().e().a(new com.xunlei.downloadprovider.ad.common.d.c(aVar, new i(this), new j(this)));
        this.b.onExposured(this.c);
        ThunderReporter.a.a(com.xunlei.downloadprovider.download.tasklist.list.a.a.h.a(this.a), SocializeProtocolConstants.PROTOCOL_KEY_TENCENT, SocializeProtocolConstants.PROTOCOL_KEY_TENCENT, this.d, BuildConfig.VERSION_NAME);
    }
}
