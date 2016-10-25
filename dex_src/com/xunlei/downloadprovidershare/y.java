package com.xunlei.downloadprovidershare;

import android.text.TextUtils;

// compiled from: ShareHelper.java
final class y implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ x b;

    y(x xVar, String str) {
        this.b = xVar;
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
