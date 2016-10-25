package com.xunlei.downloadprovider.download.tasklist.list.f;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.a.a;
import org.android.spdy.SpdyProtocol;

// compiled from: TaskDownloadRedEnvelopeView.java
final class f implements OnClickListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        this.a.c.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (this.a.g != null) {
            a.a(this.a.d, this.a.g);
            this.a.g.t = true;
        }
        if (this.a.i != null) {
            this.a.i.a = true;
        }
    }
}
