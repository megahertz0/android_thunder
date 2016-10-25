package com.xunlei.thundersniffer.sniff.sniffer;

import com.android.volley.r.a;
import com.android.volley.w;

final class s implements a {
    final /* synthetic */ String a;
    final /* synthetic */ b b;
    final /* synthetic */ SniffingDetailPageTask c;

    s(SniffingDetailPageTask sniffingDetailPageTask, String str, b bVar) {
        this.c = sniffingDetailPageTask;
        this.a = str;
        this.b = bVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder().append(wVar.toString()).append(" url = ").append(this.a);
        if (this.b != null) {
            this.b.a(this.a, null, false);
        }
    }
}
