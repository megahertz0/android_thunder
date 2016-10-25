package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnLongClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: CommentItemViewHolder.java
final class o implements OnLongClickListener {
    final /* synthetic */ j a;

    o(j jVar) {
        this.a = jVar;
    }

    public final boolean onLongClick(View view) {
        if (this.a.h != null) {
            this.a.h.a(this.a.i, SpdyProtocol.PUBKEY_PSEQ_ADASH, this.a.q);
        }
        return true;
    }
}
