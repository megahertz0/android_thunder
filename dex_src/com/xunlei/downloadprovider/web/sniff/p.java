package com.xunlei.downloadprovider.web.sniff;

import android.view.View;
import com.xunlei.downloadprovider.web.sniff.widget.d.a;
import com.xunlei.thundersniffer.sniff.SniffingResource;

// compiled from: SnifferResultsResourceAdapter.java
final class p implements a {
    final /* synthetic */ SniffingResource a;
    final /* synthetic */ View b;
    final /* synthetic */ m c;

    p(m mVar, SniffingResource sniffingResource, View view) {
        this.c = mVar;
        this.a = sniffingResource;
        this.b = view;
    }

    public final void a() {
        m.b(this.c).dismiss();
        this.c.a(this.a, this.b);
    }
}
