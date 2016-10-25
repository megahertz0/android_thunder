package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.web.sniff.widget.SniffMask.a;
import org.android.spdy.SpdyProtocol;

private class SnifferResultsFragment$o implements a {
    final /* synthetic */ SnifferResultsFragment a;

    private SnifferResultsFragment$o(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void a() {
        if (SnifferResultsFragment.v(this.a).getAlpha() > 0.9f) {
            SnifferResultsFragment.h(this.a).a(SpdyProtocol.PUBKEY_SEQ_OPEN, true);
        }
    }
}
