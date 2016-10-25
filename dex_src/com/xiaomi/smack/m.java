package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService.g;
import com.xunlei.tdlive.R;

class m extends g {
    final /* synthetic */ long b;
    final /* synthetic */ l c;

    m(l lVar, int i, long j) {
        this.c = lVar;
        this.b = j;
        super(i);
    }

    public void a() {
        if (this.c.i() && !this.c.a(this.b)) {
            this.c.y.a((int) R.styleable.Toolbar_logoDescription, null);
            this.c.y.a(true);
        }
    }

    public String b() {
        return new StringBuilder("check the ping-pong.").append(this.b).toString();
    }
}
