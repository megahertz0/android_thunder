package com.xunlei.downloadprovidershare;

import com.xunlei.downloadprovidershare.b.a.a;

// compiled from: ShareHelper.java
final class x implements a {
    final /* synthetic */ w a;

    x(w wVar) {
        this.a = wVar;
    }

    public final void a(String str) {
        this.a.b.a.a.runOnUiThread(new y(this, str));
    }
}
