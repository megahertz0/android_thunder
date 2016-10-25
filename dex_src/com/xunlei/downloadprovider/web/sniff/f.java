package com.xunlei.downloadprovider.web.sniff;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: SnifferResultsFragment.java
final class f implements OnClickListener {
    final /* synthetic */ SnifferResultsFragment a;

    f(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void onClick(View view) {
        if (SnifferResultsFragment.h(this.a).getVisibilityState() == 10) {
            SnifferResultsFragment.h(this.a).a(SpdyProtocol.PUBKEY_PSEQ_OPEN, true);
        } else {
            SnifferResultsFragment.h(this.a).a(SpdyProtocol.PUBKEY_SEQ_OPEN, true);
        }
    }
}
