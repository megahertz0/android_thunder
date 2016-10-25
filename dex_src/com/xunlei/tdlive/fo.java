package com.xunlei.tdlive;

import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.tdlive.user.e;

// compiled from: WebBrowserActivity.java
class fo implements Runnable {
    final /* synthetic */ XLOnPayListener a;
    final /* synthetic */ fn b;

    fo(fn fnVar, XLOnPayListener xLOnPayListener) {
        this.b = fnVar;
        this.a = xLOnPayListener;
    }

    public void run() {
        e.a().b(this.a);
    }
}
