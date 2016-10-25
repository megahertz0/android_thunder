package com.nostra13.universalimageloader.core;

import android.content.Context;
import com.nostra13.universalimageloader.a.a.a.a.d;
import com.nostra13.universalimageloader.a.a.a.b;
import com.nostra13.universalimageloader.b.f;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.a.c;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: DefaultConfigurationFactory.java
public final class a {

    // compiled from: DefaultConfigurationFactory.java
    private static class a implements ThreadFactory {
        private static final AtomicInteger a;
        private final ThreadGroup b;
        private final AtomicInteger c;
        private final String d;
        private final int e;

        static {
            a = new AtomicInteger(1);
        }

        a(int i, String str) {
            this.c = new AtomicInteger(1);
            this.e = i;
            this.b = Thread.currentThread().getThreadGroup();
            this.d = str + a.getAndIncrement() + "-thread-";
        }

        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.b, runnable, this.d + this.c.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.e);
            return thread;
        }
    }

    public static Executor a(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, (queueProcessingType == QueueProcessingType.LIFO ? 1 : null) != null ? new c() : new LinkedBlockingQueue(), a(i2, "uil-pool-"));
    }

    public static com.nostra13.universalimageloader.a.a.a a(Context context, com.nostra13.universalimageloader.a.a.b.a aVar, long j, int i) {
        File a = f.a(context, false);
        File file = new File(a, "uil-images");
        if (file.exists() || file.mkdir()) {
            a = file;
        }
        if (j > 0 || i > 0) {
            File a2 = f.a(context, true);
            file = new File(a2, "uil-images");
            if (file.exists() || file.mkdir()) {
                a2 = file;
            }
            try {
                return new d(a2, a, aVar, j, i);
            } catch (Throwable e) {
                com.nostra13.universalimageloader.b.c.a(e);
            }
        }
        return new b(f.a(context, true), a, aVar);
    }

    static ThreadFactory a(int i, String str) {
        return new a(i, str);
    }
}
