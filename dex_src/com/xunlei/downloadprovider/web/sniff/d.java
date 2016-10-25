package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.thundersniffer.sniff.misc.ResourceOperationListener;

// compiled from: SnifferResultsFragment.java
final class d implements ResourceOperationListener {
    final /* synthetic */ SnifferResultsFragment a;

    d(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void onResourceVodplayStatusUpdated(int i, Object obj) {
        if (SnifferResultsFragment.a(this.a) != null && SnifferResultsFragment.d(this.a) != null && SnifferResultsFragment.d(this.a).getVisibility() == 0) {
            SnifferResultsFragment.e(this.a).sendEmptyMessage(0);
        }
    }

    public final void onResourceFileInfoUpdated(int i, Object obj) {
        if (SnifferResultsFragment.a(this.a) != null && SnifferResultsFragment.d(this.a) != null && SnifferResultsFragment.d(this.a).getVisibility() == 0) {
            SnifferResultsFragment.e(this.a).sendEmptyMessage(0);
        }
    }
}
