package com.sina.weibo.sdk.statistic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WBAgentExecutor {
    private static long TIMEOUT;
    private static ExecutorService mExecutor;

    WBAgentExecutor() {
    }

    static {
        mExecutor = Executors.newSingleThreadExecutor();
        TIMEOUT = 5;
    }

    public static synchronized void execute(Runnable runnable) {
        synchronized (WBAgentExecutor.class) {
            if (mExecutor.isShutdown()) {
                mExecutor = Executors.newSingleThreadExecutor();
            }
            mExecutor.execute(runnable);
        }
    }

    public static synchronized void shutDownExecutor() {
        synchronized (WBAgentExecutor.class) {
            try {
                if (!mExecutor.isShutdown()) {
                    mExecutor.shutdown();
                }
                mExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
            } catch (Exception e) {
            }
        }
    }
}
