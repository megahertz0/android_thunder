package com.umeng.socialize.view;

import android.view.View;
import org.android.spdy.SpdyProtocol;

// compiled from: OauthDialog.java
class d implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ c c;

    d(c cVar, View view, View view2) {
        this.c = cVar;
        this.a = view;
        this.b = view2;
    }

    public void run() {
        this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (this.b.getVisibility() == 0) {
            this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        this.c.requestLayout();
    }
}
