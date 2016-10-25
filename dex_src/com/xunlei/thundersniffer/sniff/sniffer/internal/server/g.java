package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

final class g implements Runnable {
    final /* synthetic */ SVodBatchQuery a;

    g(SVodBatchQuery sVodBatchQuery) {
        this.a = sVodBatchQuery;
    }

    public final void run() {
        if (this.a.mResourceOperationMonitor != null) {
            this.a.mResourceOperationMonitor.setVodplayStatusUpdating(true);
        }
        this.a.execute();
    }
}
