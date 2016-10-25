package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: RecommendHeaderViewHolder.java
final class ab implements OnClickListener {
    final /* synthetic */ z a;

    ab(z zVar) {
        this.a = zVar;
    }

    public final void onClick(View view) {
        if (z.a(this.a) != null) {
            z.a(this.a).a(z.b(this.a), SpdyProtocol.PUBKEY_SEQ_OPEN, Boolean.valueOf(z.b(this.a).isChecked()));
        }
    }
}
