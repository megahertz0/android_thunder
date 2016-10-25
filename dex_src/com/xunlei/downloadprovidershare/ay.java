package com.xunlei.downloadprovidershare;

import android.text.TextUtils;

// compiled from: ShareHelper.java
final class ay implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ax b;

    ay(ax axVar, String str) {
        this.b = axVar;
        this.a = str;
    }

    public final void run() {
        if (TextUtils.isEmpty(this.a)) {
            d.a(this.b.a.b.a.d, this.b.a.b.a.a, this.b.a.b.a.c);
        } else {
            this.b.a.b.a.c.a = this.a;
            d.a(this.b.a.b.a.d, this.b.a.b.a.a, this.b.a.b.a.c);
        }
        d.a(this.b.a.b.a.d);
    }
}
