package com.xunlei.downloadprovidershare.b;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: ShortUrlHelp.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onErrorResponse(w wVar) {
        if (a.a(this.a) != null) {
            a.a(this.a).a(BuildConfig.VERSION_NAME);
            a.b(this.a);
        }
    }
}
