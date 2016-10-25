package com.xunlei.downloadprovider.web.base.core;

import java.util.Map;

// compiled from: BaseJsInterface.java
final class a implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Map b;
    final /* synthetic */ BaseJsInterface c;

    a(BaseJsInterface baseJsInterface, String str, Map map) {
        this.c = baseJsInterface;
        this.a = str;
        this.b = map;
    }

    public final void run() {
        this.c.callbackDirect(this.a, this.b);
    }
}
