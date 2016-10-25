package com.xunlei.downloadprovider.web.sniff;

import android.os.Handler;
import android.os.Message;

// compiled from: SnifferResultsFragment.java
final class c extends Handler {
    final /* synthetic */ SnifferResultsFragment a;

    c(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void handleMessage(Message message) {
        SnifferResultsFragment.a(this.a).notifyDataSetChanged();
    }
}
