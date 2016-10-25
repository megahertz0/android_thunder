package com.xunlei.downloadprovider.download.tasklist.list.f;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: TaskDownloadRedEnvelopeView.java
final class g implements OnClickListener {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        a d = this.a.i;
        d.c.a(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (d.b != null) {
            d.b.s = false;
            d.b.t = true;
        }
    }
}
