package com.xunlei.thundersniffer.sniff.sniffer;

import com.android.volley.r.a;
import com.android.volley.w;

final class i implements a {
    final /* synthetic */ SnifferSvrCheckWordOperation a;

    i(SnifferSvrCheckWordOperation snifferSvrCheckWordOperation) {
        this.a = snifferSvrCheckWordOperation;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("requestCheckWord: error --> ").append(wVar.toString());
        SnifferSvrCheckWordOperation.a(this.a, false);
        this.a.finish();
    }
}
