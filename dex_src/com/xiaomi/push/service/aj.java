package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.g;
import com.xunlei.tdlive.R;

class aj extends g {
    final /* synthetic */ XMPushService b;

    aj(XMPushService xMPushService, int i) {
        this.b = xMPushService;
        super(i);
    }

    public void a() {
        if (this.b.f()) {
            this.b.a((int) R.styleable.Toolbar_collapseIcon, null);
        }
    }

    public String b() {
        return "disconnect because of connecting timeout";
    }
}
