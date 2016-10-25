package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.user.f;

// compiled from: BaseNormalScreenLayoutPresenter.java
class k implements Runnable {
    final /* synthetic */ c a;

    k(c cVar) {
        this.a = cVar;
    }

    public void run() {
        if (!this.a.k && f.a().b()) {
            c.a(this.a);
        }
    }
}
