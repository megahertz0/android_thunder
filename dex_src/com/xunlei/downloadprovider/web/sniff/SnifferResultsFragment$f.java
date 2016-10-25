package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff;
import com.xunlei.downloadprovidershare.R;
import org.android.spdy.SpdyProtocol;

private class SnifferResultsFragment$f extends SnifferResultsFragment$c {
    final /* synthetic */ SnifferResultsFragment b;
    private boolean c;

    private SnifferResultsFragment$f(SnifferResultsFragment snifferResultsFragment) {
        this.b = snifferResultsFragment;
        super(snifferResultsFragment, (byte) 0);
    }

    final void a() {
        new StringBuilder(" performUIChange ").append(getClass().getSimpleName());
        if (!this.c) {
            Sniff.a();
            this.c = true;
        }
        if (SnifferResultsFragment.b(this.b) != null) {
            SnifferResultsFragment.b(this.b).b(false);
        }
        if (this.b.getView() != null) {
            this.b.getView().setVisibility(0);
            SnifferResultsFragment.h(this.b).a(SpdyProtocol.PUBKEY_PSEQ_OPEN, true);
            SnifferResultsFragment.F(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.D(this.b).setVisibility(0);
            SnifferResultsFragment.C(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.s(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.x(this.b).setNumberText(0);
            SnifferResultsFragment.x(this.b).setText(this.b.getActivity().getString(R.string.invalid_network));
            SnifferResultsFragment.P(this.b).setText(this.b.getActivity().getString(R.string.invalid_network));
            SnifferResultsFragment.Q(this.b).setText(this.b.getActivity().getString(com.xunlei.downloadprovider.R.string.click_refresh));
            SnifferResultsFragment.R(this.b).setText(com.xunlei.downloadprovider.R.string.browser_refresh);
            SnifferResultsFragment.E(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.u(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.d(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (SnifferResultsFragment.b(this.b) != null) {
                SnifferResultsFragment.b(this.b).a(false);
            }
        } else if (SnifferResultsFragment.b(this.b) != null) {
            SnifferResultsFragment.b(this.b).a(true);
        }
    }
}
