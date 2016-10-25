package anetwork.channel.entity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: Taobao
public final class d {
    private static final ExecutorService[] a;
    private static AtomicInteger b;

    static {
        int i = 0;
        a = new ExecutorService[2];
        b = new AtomicInteger(0);
        while (i < 2) {
            a[i] = Executors.newSingleThreadExecutor(new e());
            i++;
        }
    }

    public static void a(int i, Runnable runnable) {
        a[Math.abs(i % 2)].submit(runnable);
    }
}
