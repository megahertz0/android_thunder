package com.xunlei.downloadprovider.model.protocol.g;

import android.os.Handler;
import anet.channel.util.HttpConstant;
import com.xunlei.downloadprovider.b.a;

// compiled from: XunleiScanCodeBox.java
public final class e extends a {
    public e(Handler handler, Object obj) {
        super(handler, obj);
    }

    public final int a(String str) {
        com.xunlei.downloadprovider.b.b.a aVar = new com.xunlei.downloadprovider.b.b.a(new StringBuilder("http://status.u.155.com/download/notify?checkcode=").append(str).toString(), null);
        aVar.a(HttpConstant.ACCEPT, "application/json");
        aVar.a = new h(this);
        setBpFuture(aVar);
        return a.runBox(this);
    }
}
