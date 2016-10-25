package anetwork.channel.h;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: Taobao
public final class a {
    private static volatile ScheduledThreadPoolExecutor a;
    private static int b;
    private static final AtomicInteger c;

    // compiled from: Taobao
    static class a implements ThreadFactory {
        a() {
        }

        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, new StringBuilder("ANet:").append(c.getAndIncrement()).toString());
            thread.setPriority(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            return thread;
        }
    }

    static {
        b = 2;
        c = new AtomicInteger();
    }

    public static synchronized ScheduledThreadPoolExecutor a() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (a.class) {
            if (a == null) {
                a = new ScheduledThreadPoolExecutor(b, new a());
            }
            scheduledThreadPoolExecutor = a;
        }
        return scheduledThreadPoolExecutor;
    }
}
