package com.xiaomi.account.openauth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class XiaomiOAuthRunnable<V> implements Runnable {
    private static ExecutorService sTaskExecutor;
    protected XiaomiOAuthFutureImpl<V> mFuture;

    protected abstract void doRun();

    XiaomiOAuthRunnable() {
        this.mFuture = new XiaomiOAuthFutureImpl();
    }

    static {
        sTaskExecutor = Executors.newCachedThreadPool();
    }

    XiaomiOAuthFutureImpl<V> start() {
        sTaskExecutor.execute(this);
        return this.mFuture;
    }

    public final void run() {
        doRun();
    }
}
