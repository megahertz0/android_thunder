package com.xunlei.downloadprovider.web.base.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import org.android.spdy.SpdyProtocol;

// compiled from: CommentItemViewHolder.java
final class t implements OnClickListener {
    final /* synthetic */ j a;

    t(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        if (this.a.q.m) {
            XLToast.b(view.getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u60a8\u5df2\u70b9\u8d5e\u8fc7");
            return;
        }
        j.k(this.a);
        if (this.a.h != null) {
            this.a.h.a(this.a.p, SpdyProtocol.PUBKEY_PSEQ_OPEN, this.a.q);
        }
    }
}
