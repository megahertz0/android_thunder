package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.downloadprovider.web.sniff.widget.RiseNumberTextView;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyProtocol;

private class SnifferResultsFragment$m extends SnifferResultsFragment$c {
    final /* synthetic */ SnifferResultsFragment b;
    private List<SniffingResourceGroup> c;
    private String d;

    public SnifferResultsFragment$m(SnifferResultsFragment snifferResultsFragment, String str) {
        this.b = snifferResultsFragment;
        super((byte) 0);
        this.d = str;
        this.c = new ArrayList();
    }

    final void a(SniffingResourceGroup sniffingResourceGroup) {
        new StringBuilder("5 -- add group data --> sniffRg.realUrl --> ").append(sniffingResourceGroup.realUrl).append(", sniffRg.size() --> ").append(sniffingResourceGroup.resources.size());
        if (this.c != null && !d.a(sniffingResourceGroup.resources)) {
            this.c.add(sniffingResourceGroup);
        }
    }

    public final void a() {
        new StringBuilder(" performUIChange ").append(getClass().getSimpleName());
        if (this.c != null && this.c.size() != 0) {
            SniffingResourceGroup sniffingResourceGroup = (SniffingResourceGroup) this.c.get(this.c.size() - 1);
            if (!d.a(sniffingResourceGroup.resources)) {
                String searchWordFromUrl;
                CharSequence string;
                int size;
                this.b.getView().setVisibility(0);
                SnifferResultsFragment.F(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.s(this.b).setVisibility(0);
                SnifferResultsFragment.C(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.D(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.E(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                SnifferResultsFragment.u(this.b).setVisibility(0);
                SnifferResultsFragment.d(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (SnifferResultsFragment.I(this.b) == null) {
                    SnifferResultsFragment.a(this.b, new r(this.b.getActivity()));
                }
                if (SnifferResultsFragment.u(this.b).getAdapter() == null) {
                    SnifferResultsFragment.u(this.b).setAdapter(SnifferResultsFragment.I(this.b));
                }
                SnifferResultsFragment.I(this.b).c = true;
                new StringBuilder("6 -- add group data --> foundResGroup.realUrl --> ").append(sniffingResourceGroup.realUrl).append(", foundResGroup.resources.size() --> ").append(sniffingResourceGroup.resources.size());
                SnifferResultsFragment.I(this.b).a(sniffingResourceGroup);
                SnifferResultsFragment.h(this.b).setContentListId(R.id.sniffer_page_results_list_view);
                SnifferResultsFragment.x(this.b).setNumberPrefix(this.b.getActivity().getString(R.string.text_excuting_sniff_found_preffix));
                if (f.e(ThunderSnifferUtil.getSearchWordFromUrl(this.d)) == null || !f.e(ThunderSnifferUtil.getSearchWordFromUrl(this.d)).trim().equals("\u8fc5\u96f7\u4e0b\u8f7d")) {
                    searchWordFromUrl = ThunderSnifferUtil.getSearchWordFromUrl(this.d);
                } else {
                    searchWordFromUrl = f.d(ThunderSnifferUtil.getSearchWordFromUrl(this.d));
                }
                if (searchWordFromUrl == null || searchWordFromUrl.trim().equals(BuildConfig.VERSION_NAME)) {
                    string = this.b.getActivity().getString(R.string.text_excuting_sniff_found_suffix);
                } else {
                    string = new StringBuilder("\u4e2a \"").append(searchWordFromUrl).append("\" ").append(this.b.getActivity().getString(R.string.text_excuting_sniff_found_suffix)).toString();
                }
                SnifferResultsFragment.x(this.b).setNumberSuffix(string);
                RiseNumberTextView x = SnifferResultsFragment.x(this.b);
                if (this.c != null) {
                    size = this.c.size();
                } else {
                    size = 0;
                }
                x.setNumberText(size);
                SnifferResultsFragment.J(this.b).setText(SnifferResultsFragment.x(this.b).getText().toString());
                if (SnifferResultsFragment.h(this.b).getVisibilityState() == 10 && SnifferResultsFragment.x(this.b).getTextNumber() != 0) {
                    SnifferResultsFragment.w(this.b);
                }
            }
        }
    }
}
