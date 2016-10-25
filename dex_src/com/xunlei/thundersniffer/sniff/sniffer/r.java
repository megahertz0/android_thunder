package com.xunlei.thundersniffer.sniff.sniffer;

import com.android.volley.r.b;

final class r implements b<String> {
    final /* synthetic */ b a;
    final /* synthetic */ String b;
    final /* synthetic */ SniffingDetailPageTask c;

    r(SniffingDetailPageTask sniffingDetailPageTask, b bVar, String str) {
        this.c = sniffingDetailPageTask;
        this.a = bVar;
        this.b = str;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        if (this.a != null) {
            this.a.a(this.b, str, true);
        }
    }
}
