package com.xunlei.tdlive;

import com.xunlei.tdlive.WebBrowserActivity.c;
import org.android.spdy.SpdyProtocol;

// compiled from: WebBrowserActivity.java
class fy implements Runnable {
    final /* synthetic */ c a;

    fy(c cVar) {
        this.a = cVar;
    }

    public void run() {
        WebBrowserActivity.access$700(this.a.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
