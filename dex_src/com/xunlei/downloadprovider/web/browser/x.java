package com.xunlei.downloadprovider.web.browser;

import org.apache.commons.logging.impl.SimpleLog;

// compiled from: BrowserSniffer.java
final class x implements Runnable {
    final /* synthetic */ w a;

    x(w wVar) {
        this.a = wVar;
    }

    public final void run() {
        if (this.a.a.h == 2) {
            BrowserSniffer.a(this.a.a, SimpleLog.LOG_LEVEL_DEBUG);
        }
    }
}
