package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService.g;

class n extends g {
    final /* synthetic */ int b;
    final /* synthetic */ Exception c;
    final /* synthetic */ l d;

    n(l lVar, int i, int i2, Exception exception) {
        this.d = lVar;
        this.b = i2;
        this.c = exception;
        super(i);
    }

    public void a() {
        this.d.y.a(this.b, this.c);
    }

    public String b() {
        return new StringBuilder("shutdown the connection. ").append(this.b).append(", ").append(this.c).toString();
    }
}
