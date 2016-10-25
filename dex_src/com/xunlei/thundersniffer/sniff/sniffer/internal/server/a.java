package com.xunlei.thundersniffer.sniff.sniffer.internal.server;

final class a implements Runnable {
    final /* synthetic */ BatchQueryOperation a;

    a(BatchQueryOperation batchQueryOperation) {
        this.a = batchQueryOperation;
    }

    public final void run() {
        this.a.checkNextInner();
    }
}
