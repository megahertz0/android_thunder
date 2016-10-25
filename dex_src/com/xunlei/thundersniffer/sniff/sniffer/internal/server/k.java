package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

import com.android.volley.r.a;
import com.android.volley.w;

final class k implements a {
    final /* synthetic */ SVodBatchQuery a;

    k(SVodBatchQuery sVodBatchQuery) {
        this.a = sVodBatchQuery;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("vod query error: ").append(wVar);
        this.a.finish();
    }
}
