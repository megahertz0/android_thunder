package com.taobao.accs.common;

import com.taobao.accs.utl.ALog;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: Taobao
public class a {
    private static volatile ScheduledThreadPoolExecutor a;
    private static volatile ScheduledThreadPoolExecutor b;
    private static final AtomicInteger c;

    // compiled from: Taobao
    static class a implements ThreadFactory {
        private String a;

        public a(String str) {
            this.a = str;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, new StringBuilder("tag:").append(c.getAndIncrement()).toString());
            thread.setPriority(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            return thread;
        }
    }

    static {
        c = new AtomicInteger();
    }

    public static ScheduledThreadPoolExecutor a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new ScheduledThreadPoolExecutor(1, new a("ACCS"));
                }
            }
        }
        return a;
    }

    public static ScheduledFuture<?> a(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            return a().schedule(runnable, j, timeUnit);
        } catch (Throwable th) {
            ALog.e("ThreadPoolExecutorFactory", "ThreadPoolExecutorFactory schedule", th, new Object[0]);
            return null;
        }
    }

    public static void a(Runnable runnable) {
        try {
            a().execute(runnable);
        } catch (Throwable th) {
            ALog.e("ThreadPoolExecutorFactory", "ThreadPoolExecutorFactory execute", th, new Object[0]);
        }
    }

    public static ScheduledThreadPoolExecutor b() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new ScheduledThreadPoolExecutor(1, new a("ACCS SEND"));
                }
            }
        }
        return b;
    }
}
