package com.xunlei.downloadprovider.util;

import com.xunlei.downloadprovider.util.sniff.SniffConfigure;

// compiled from: OnlineConfigure.java
public final class s implements Runnable {
    final /* synthetic */ r a;

    public s(r rVar) {
        this.a = rVar;
    }

    public final void run() {
        this.a.d();
        SniffConfigure.a().f();
    }
}
