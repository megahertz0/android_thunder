package com.xunlei.downloadprovidershare;

import com.xunlei.downloadprovidershare.b.d.a;

// compiled from: ShareHelper.java
final class m implements a {
    final /* synthetic */ f a;

    m(f fVar) {
        this.a = fVar;
    }

    public final void a(String str) {
        this.a.a.runOnUiThread(new n(this, str));
    }
}
