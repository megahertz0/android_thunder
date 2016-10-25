package com.xunlei.downloadprovider.model.protocol.report;

import android.content.Context;
import com.xunlei.XLStat.XLStat;

// compiled from: HubbleReportBox.java
final class c implements Runnable {
    final /* synthetic */ Context a;

    c(Context context) {
        this.a = context;
    }

    public final void run() {
        b.a(b.b(this.a, b.c()));
        b.a(b.d());
        b.e();
        new StringBuilder("init result:").append(b.f() != null).append("xlstat version: ").append(XLStat.getSdkVersion());
    }
}
