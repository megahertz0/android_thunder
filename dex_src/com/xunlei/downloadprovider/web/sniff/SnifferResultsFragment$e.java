package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.R;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import org.android.spdy.SpdyProtocol;

private class SnifferResultsFragment$e extends SnifferResultsFragment$c {
    SniffingResourceGroup b;
    final /* synthetic */ SnifferResultsFragment c;

    private SnifferResultsFragment$e(SnifferResultsFragment snifferResultsFragment) {
        this.c = snifferResultsFragment;
        super(snifferResultsFragment, (byte) 0);
    }

    public final void a() {
        new StringBuilder(" performUIChange ").append(getClass().getSimpleName());
        this.c.getView().setVisibility(0);
        SnifferResultsFragment.u(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.d(this.c).setVisibility(0);
        SnifferResultsFragment.s(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.C(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.D(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.E(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.F(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (this.b != null) {
            SnifferResultsFragment.x(this.c).setText(a(this.b.count));
            if (SnifferResultsFragment.d(this.c) != null) {
                if (SnifferResultsFragment.a(this.c) == null) {
                    SnifferResultsFragment.a(this.c, new m(this.c.getActivity()));
                    SnifferResultsFragment.a(this.c).f = SnifferResultsFragment.G(this.c);
                }
                SnifferResultsFragment.a(this.c).a();
                SnifferResultsFragment.a(this.c).c = false;
                SnifferResultsFragment.d(this.c).setAdapter(SnifferResultsFragment.a(this.c));
                SnifferResultsFragment.a(this.c).a(this.b.resources);
                if (!(this.b == null || this.b.getResourceOperationMonitor() == null)) {
                    this.b.getResourceOperationMonitor().setListener(SnifferResultsFragment.H(this.c));
                }
            }
        }
        SnifferResultsFragment.h(this.c).setContentListId(R.id.sniffer_res_results_list_view);
    }
}
