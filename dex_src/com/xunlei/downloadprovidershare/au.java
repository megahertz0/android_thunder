package com.xunlei.downloadprovidershare;

import android.text.TextUtils;

// compiled from: ShareHelper.java
final class au implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ at b;

    au(at atVar, String str) {
        this.b = atVar;
        this.a = str;
    }

    public final void run() {
        if (TextUtils.isEmpty(this.a)) {
            d.a(this.b.a.b.a.d, this.b.a.b.a.c);
        } else {
            this.b.a.b.a.c.a = this.a;
            d.a(this.b.a.b.a.d, this.b.a.b.a.c);
        }
        d.a(this.b.a.b.a.d);
    }
}
