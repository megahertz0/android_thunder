package com.xunlei.downloadprovidershare.a;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: SharePlatformsForPlayerDialog.java
final class d implements OnClickListener {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        b.a(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        b.b(this.a).setVisibility(0);
        b.c(this.a).setVisibility(0);
        if (b.d(this.a) != null) {
            b.d(this.a).setVisibility(0);
        }
        if (b.e(this.a) != null) {
            b.e(this.a).setVisibility(0);
        }
    }
}
