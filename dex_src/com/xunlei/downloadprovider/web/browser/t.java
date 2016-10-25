package com.xunlei.downloadprovider.web.browser;

import java.util.TimerTask;

// compiled from: BrowserSniffer.java
final class t extends TimerTask {
    final /* synthetic */ BrowserSniffer a;

    t(BrowserSniffer browserSniffer) {
        this.a = browserSniffer;
    }

    public final void run() {
        if (this == this.a.n) {
            this.a.f.post(new u(this));
        }
    }
}
