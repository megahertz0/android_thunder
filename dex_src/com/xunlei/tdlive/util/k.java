package com.xunlei.tdlive.util;

import com.xunlei.tdlive.protocol.XLLiveComplainRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.user.f.b;

// compiled from: RequestHelper.java
class k implements b {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void a(boolean z) {
        if (z) {
            new XLLiveComplainRequest(f.a().k(), f.a().l(), this.a.b, this.a.c, 0, this.a.d).send(new l(this));
        }
    }
}
