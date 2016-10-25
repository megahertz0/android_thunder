package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

private class SnifferResultsFragment$i extends SnifferResultsFragment$c {
    final /* synthetic */ SnifferResultsFragment b;

    private SnifferResultsFragment$i(SnifferResultsFragment snifferResultsFragment) {
        this.b = snifferResultsFragment;
        super((byte) 0);
    }

    public final void a() {
        new StringBuilder(" performUIChange ").append(getClass().getSimpleName());
        if (SnifferResultsFragment.j(this.b) != 0) {
            b();
            this.b.getView().setVisibility(0);
            SnifferResultsFragment.F(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.A(this.b).setVisibility(0);
            if (!SnifferResultsFragment.K(this.b)) {
                SnifferResultsFragment.v(this.b).setAlpha(1.0f);
                SnifferResultsFragment.v(this.b).setVisibility(0);
            }
            SnifferResultsFragment.h(this.b).a(SpdyProtocol.PUBKEY_PSEQ_OPEN, SnifferResultsFragment.K(this.b));
            SnifferResultsFragment.L(this.b);
            SnifferResultsFragment.u(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.d(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.u(this.b).setAdapter(null);
            SnifferResultsFragment.d(this.b).setAdapter(null);
            if (SnifferResultsFragment.a(this.b) != null) {
                SnifferResultsFragment.a(this.b).a();
            }
            if (SnifferResultsFragment.I(this.b) != null) {
                SnifferResultsFragment.I(this.b).a();
            }
            SnifferResultsFragment.s(this.b).setSmoothProgress(0);
            SnifferResultsFragment.s(this.b).setVisibility(0);
            SnifferResultsFragment.C(this.b).setVisibility(0);
            SnifferResultsFragment.D(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.E(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.b.getView().setVisibility(0);
            if (SnifferResultsFragment.b(this.b) != null) {
                SnifferResultsFragment.b(this.b).a(false);
            }
            SnifferResultsFragment.x(this.b).setText(this.b.getActivity().getString(R.string.text_excuting_sniff_resource));
            SnifferResultsFragment.z(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            SnifferResultsFragment.z(this.b).setAlpha(0.0f);
            SnifferResultsFragment.y(this.b);
            SnifferResultsFragment.M(this.b);
        }
    }
}
