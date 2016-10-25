package com.xunlei.downloadprovider.web.sniff;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xllib.a.b;
import org.android.spdy.SpdyProtocol;

// compiled from: SnifferResultsFragment.java
final class j implements OnClickListener {
    final /* synthetic */ SnifferResultsFragment a;

    j(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void onClick(View view) {
        if (SnifferResultsFragment.b(this.a) != null && b.a(this.a.getActivity())) {
            SnifferResultsFragment.a(this.a, SpdyProtocol.PUBKEY_PSEQ_OPEN);
            SnifferResultsFragment.c(this.a);
            SnifferResultsFragment.b(this.a).b();
        }
        String str = "sniff_6_offline_click";
        Sniff.a(g.a("android_sniff", str, str));
    }
}
