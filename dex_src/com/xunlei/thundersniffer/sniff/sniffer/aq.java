package com.xunlei.thundersniffer.sniff.sniffer;

import com.android.volley.r.a;
import com.android.volley.w;

final class aq implements a {
    final /* synthetic */ ao a;

    aq(ao aoVar) {
        this.a = aoVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("sniffer.set error: ").append(wVar.toString());
    }
}
