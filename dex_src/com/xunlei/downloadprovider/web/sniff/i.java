package com.xunlei.downloadprovider.web.sniff;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SnifferResultsFragment.java
final class i implements OnClickListener {
    final /* synthetic */ SnifferResultsFragment a;

    i(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void onClick(View view) {
        if (SnifferResultsFragment.b(this.a) != null) {
            SnifferResultsFragment.b(this.a).c();
        }
    }
}
