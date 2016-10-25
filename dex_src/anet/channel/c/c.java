package anet.channel.c;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: Taobao
public class c {
    private static volatile ScheduledExecutorService a;
    private static volatile ThreadPoolExecutor b;
    private static volatile b c;

    // compiled from: Taobao
    static class a implements ThreadFactory {
        AtomicInteger a;
        String b;

        a(String str) {
            this.a = new AtomicInteger(0);
            this.b = str;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.b + this.a.incrementAndGet());
            thread.setPriority(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            return thread;
        }
    }

    public static Future<?> a(Runnable runnable) {
        return a().submit(runnable);
    }

    public static ScheduledFuture<?> a(Runnable runnable, long j, TimeUnit timeUnit) {
        return a().schedule(runnable, j, timeUnit);
    }

    public static Future<?> a(Runnable runnable, int i) {
        Object aVar = new a(runnable, i);
        b().submit(aVar);
        return aVar;
    }

    static ScheduledExecutorService a() {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new ScheduledThreadPoolExecutor(1, new a("AWCN Scheduler"));
                }
            }
        }
        return a;
    }

    static ThreadPoolExecutor b() {
        if (c == null) {
            synchronized (c.class) {
                if (c == null) {
                    c = new b(1, 1, 300, TimeUnit.SECONDS, new PriorityBlockingQueue(), new a("AWCN Dispatcher"));
                }
            }
        }
        return c;
    }

    static ThreadPoolExecutor c() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new ThreadPoolExecutor(2, 7, 60, TimeUnit.SECONDS, new ArrayBlockingQueue(16), new a("AWCN Worker"));
                }
            }
        }
        return b;
    }
}
