package com.xunlei.downloadprovider.web.base;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.e;

// compiled from: KandanListActivity.java
final class i implements Runnable {
    final /* synthetic */ KandanListActivity a;

    i(KandanListActivity kandanListActivity) {
        this.a = kandanListActivity;
    }

    public final void run() {
        e.a(this.a.D, this.a.b);
    }
}
