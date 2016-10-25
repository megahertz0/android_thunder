package com.xiaomi.smack;

import com.xiaomi.network.HostManager;

class o implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ l b;

    o(l lVar, String str) {
        this.b = lVar;
        this.a = str;
    }

    public void run() {
        HostManager.getInstance().getFallbacksByHost(this.a, true);
    }
}
