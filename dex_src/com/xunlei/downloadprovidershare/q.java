package com.xunlei.downloadprovidershare;

import com.xunlei.downloadprovidershare.b.d.a;

// compiled from: ShareHelper.java
final class q implements a {
    final /* synthetic */ f a;

    q(f fVar) {
        this.a = fVar;
    }

    public final void a(String str) {
        this.a.a.runOnUiThread(new s(this, str));
    }
}
