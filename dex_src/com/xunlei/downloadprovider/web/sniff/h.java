package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.thundersniffer.sniff.SniffingResource;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: SnifferResultsFragment.java
final class h implements m$a {
    final /* synthetic */ SnifferResultsFragment a;

    h(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void a(int i, SniffingResource sniffingResource) {
        SnifferResultsFragment snifferResultsFragment;
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                snifferResultsFragment = this.a;
                this.a.getActivity();
                SnifferResultsFragment.a(snifferResultsFragment, sniffingResource);
            case SimpleLog.LOG_LEVEL_DEBUG:
                snifferResultsFragment = this.a;
                this.a.getActivity();
                SnifferResultsFragment.b(snifferResultsFragment, sniffingResource);
            default:
                break;
        }
    }
}
