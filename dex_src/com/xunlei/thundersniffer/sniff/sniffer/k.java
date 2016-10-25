package com.xunlei.thundersniffer.sniff.sniffer;

import com.android.volley.r.a;
import com.android.volley.w;

final class k implements a {
    final /* synthetic */ SnifferSvrGetOperation a;

    k(SnifferSvrGetOperation snifferSvrGetOperation) {
        this.a = snifferSvrGetOperation;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder().append(this.a.d.a).append(" sniffer.get error: ").append(wVar.toString());
        SnifferSvrGetOperation.a(this.a).b("Sniffer.SnifferSvrGetOperation", "SvrGetRequest End");
        this.a.finish();
    }
}
