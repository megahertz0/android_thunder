package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.downloadprovider.member.payment.ui.widget.a.a;

// compiled from: BasePayActivity.java
final class i implements a {
    final /* synthetic */ com.xunlei.downloadprovider.member.payment.ui.widget.a a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;
    final /* synthetic */ BasePayActivity f;

    i(BasePayActivity basePayActivity, com.xunlei.downloadprovider.member.payment.ui.widget.a aVar, String str, int i, int i2, int i3) {
        this.f = basePayActivity;
        this.a = aVar;
        this.b = str;
        this.c = i;
        this.d = i2;
        this.e = i3;
    }

    public final void a() {
        this.a.cancel();
        this.f.a(this.b, this.c, this.d, this.e, null, 0);
    }

    public final void b() {
        this.a.cancel();
        this.f.b(this.b, this.c, this.d, this.e, null, 0);
    }
}
