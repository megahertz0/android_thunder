package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.downloadprovider.member.payment.ui.widget.b.a;
import com.xunlei.downloadprovider.member.payment.ui.widget.b.c;

// compiled from: BasePayPageFragment.java
final class q implements a {
    final /* synthetic */ BasePayPageFragment a;

    q(BasePayPageFragment basePayPageFragment) {
        this.a = basePayPageFragment;
    }

    public final void a(c cVar) {
        float j = this.a.d + ((float) this.a.j());
        float b = BasePayPageFragment.b(this.a) - ((float) this.a.j());
        BasePayPageFragment.a(this.a, cVar);
        BasePayPageFragment.a(this.a, true);
        this.a.a(j, b);
    }

    public final void a() {
        BasePayPageFragment.a(this.a, BasePayPageFragment.c(this.a));
    }
}
