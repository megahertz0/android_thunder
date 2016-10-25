package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

final class b implements Runnable {
    final /* synthetic */ SHubBatchQuery a;

    b(SHubBatchQuery sHubBatchQuery) {
        this.a = sHubBatchQuery;
    }

    public final void run() {
        if (this.a.mResourceOperationMonitor != null) {
            this.a.mResourceOperationMonitor.setFileInfoUpdating(true);
        }
        this.a.execute();
    }
}
