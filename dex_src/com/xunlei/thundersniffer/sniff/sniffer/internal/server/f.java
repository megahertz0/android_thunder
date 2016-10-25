package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import com.android.volley.r.a;
import com.android.volley.w;

final class f implements a {
    final /* synthetic */ SHubBatchQuery a;

    f(SHubBatchQuery sHubBatchQuery) {
        this.a = sHubBatchQuery;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("shub query error: ").append(wVar);
        this.a.finish();
    }
}
