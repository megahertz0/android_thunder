package com.xunlei.downloadprovider.web.sniff;

import org.android.spdy.SpdyProtocol;

private class SnifferResultsFragment$l extends SnifferResultsFragment$c {
    final /* synthetic */ SnifferResultsFragment b;

    private SnifferResultsFragment$l(SnifferResultsFragment snifferResultsFragment) {
        this.b = snifferResultsFragment;
        super((byte) 0);
    }

    final void a() {
        new StringBuilder(" performUIChange ").append(getClass().getSimpleName()).append(" getLastIndexFlag(0): ").append(SnifferResultsFragment.d(this.b, 0)).append(" getLastIndexFlag(1): ").append(SnifferResultsFragment.d(this.b, 1));
        if (SnifferResultsFragment.d(this.b, 1) != 3) {
            this.b.getView().setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.u(this.b).setAdapter(null);
            SnifferResultsFragment.d(this.b).setAdapter(null);
            b();
            if (SnifferResultsFragment.a(this.b) != null) {
                SnifferResultsFragment.a(this.b).a();
            }
            if (SnifferResultsFragment.I(this.b) != null) {
                SnifferResultsFragment.I(this.b).a();
            }
            if (SnifferResultsFragment.b(this.b) != null) {
                SnifferResultsFragment.b(this.b).a(true);
                SnifferResultsFragment.b(this.b).b(true);
                SnifferResultsFragment.b(this.b).d();
            }
        }
    }
}
