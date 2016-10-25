package com.xunlei.downloadprovidershare;

import com.xunlei.downloadprovidershare.b.d.a;

// compiled from: ShareHelper.java
final class ah implements a {
    final /* synthetic */ ag a;

    ah(ag agVar) {
        this.a = agVar;
    }

    public final void a(String str) {
        this.a.a.runOnUiThread(new ai(this, str));
    }
}
