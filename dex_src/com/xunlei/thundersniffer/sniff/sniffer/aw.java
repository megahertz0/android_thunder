package com.xunlei.thundersniffer.sniff.sniffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class aw {
    private static aw b;
    ExecutorService a;

    static {
        b = new aw();
    }

    public static aw a() {
        return b;
    }

    private aw() {
    }

    final boolean a(SniffingTask sniffingTask) {
        if (sniffingTask == null) {
            return false;
        }
        sniffingTask.a(SniffingTask.i);
        if (this.a == null) {
            this.a = Executors.newCachedThreadPool();
        }
        this.a.execute(sniffingTask);
        return true;
    }
}
