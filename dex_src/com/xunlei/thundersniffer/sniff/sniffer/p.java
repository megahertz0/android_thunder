package com.xunlei.thundersniffer.sniff.sniffer;

import com.android.volley.r.a;
import com.android.volley.w;

final class p implements a {
    final /* synthetic */ String a;
    final /* synthetic */ b b;
    final /* synthetic */ long c;
    final /* synthetic */ SniffingDetailPageTask d;

    p(SniffingDetailPageTask sniffingDetailPageTask, String str, b bVar, long j) {
        this.d = sniffingDetailPageTask;
        this.a = str;
        this.b = bVar;
        this.c = j;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder().append(wVar.toString()).append(" url = ").append(this.a);
        SniffingDetailPageTask.b(this.d);
        if (!this.d.x) {
            this.d.w = true;
            if (this.b != null) {
                new StringBuilder("spend_time_2 --> ").append(System.currentTimeMillis() - this.c);
                this.b.a(this.a, null, false);
            }
        }
    }
}
