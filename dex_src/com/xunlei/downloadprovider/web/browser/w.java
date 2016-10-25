package com.xunlei.downloadprovider.web.browser;

import java.util.TimerTask;

// compiled from: BrowserSniffer.java
final class w extends TimerTask {
    final /* synthetic */ BrowserSniffer a;

    w(BrowserSniffer browserSniffer) {
        this.a = browserSniffer;
    }

    public final void run() {
        if (this == this.a.o) {
            this.a.f.post(new x(this));
        }
    }
}
