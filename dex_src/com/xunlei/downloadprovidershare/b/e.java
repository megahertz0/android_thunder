package com.xunlei.downloadprovidershare.b;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: WeChatReportHelper.java
final class e implements a {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder().append(wVar);
        if (d.a(this.a) != null) {
            d.a(this.a).a(BuildConfig.VERSION_NAME);
            d.b(this.a);
        }
    }
}
