package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: RecommendFooterViewHolder.java
final class y implements OnClickListener {
    final /* synthetic */ x a;

    y(x xVar) {
        this.a = xVar;
    }

    public final void onClick(View view) {
        if (x.a(this.a) != null) {
            x.a(this.a).a(x.b(this.a), SpdyProtocol.PUBKEY_SEQ_ADASH, null);
        }
    }
}
