package com.xiaomi.channel.commonutils.logger;

import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class b {
    private static int a;
    private static LoggerInterface b;
    private static final HashMap<Integer, Long> c;
    private static final HashMap<Integer, String> d;
    private static final Integer e;
    private static AtomicInteger f;

    static {
        a = 2;
        b = new a();
        c = new HashMap();
        d = new HashMap();
        e = Integer.valueOf(-1);
        f = new AtomicInteger(1);
    }

    public static int a() {
        return a;
    }

    public static void a(int i, String str) {
        if (i >= a) {
            b.log(str);
        }
    }

    public static void a(int i, String str, Throwable th) {
        if (i >= a) {
            b.log(str, th);
        }
    }

    public static void a(int i, Throwable th) {
        if (i >= a) {
            b.log(a.d, th);
        }
    }

    public static void a(LoggerInterface loggerInterface) {
        b = loggerInterface;
    }

    public static void a(Integer num) {
        if (a <= 1 && c.containsKey(num)) {
            long currentTimeMillis = System.currentTimeMillis() - ((Long) c.remove(num)).longValue();
            b.log(((String) d.remove(num)) + " ends in " + currentTimeMillis + " ms");
        }
    }

    public static void a(String str) {
        a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, new StringBuilder("[Thread:").append(Thread.currentThread().getId()).append("] ").append(str).toString());
    }

    public static void a(String str, Throwable th) {
        a(XZBDevice.DOWNLOAD_LIST_ALL, str, th);
    }

    public static void a(Throwable th) {
        a((int) XZBDevice.DOWNLOAD_LIST_ALL, th);
    }

    public static void b(String str) {
        a(0, str);
    }

    public static void c(String str) {
        a(1, new StringBuilder("[Thread:").append(Thread.currentThread().getId()).append("] ").append(str).toString());
    }

    public static void d(String str) {
        a((int) XZBDevice.DOWNLOAD_LIST_ALL, str);
    }

    public static Integer e(String str) {
        if (a > 1) {
            return e;
        }
        Integer valueOf = Integer.valueOf(f.incrementAndGet());
        c.put(valueOf, Long.valueOf(System.currentTimeMillis()));
        d.put(valueOf, str);
        b.log(str + " starts");
        return valueOf;
    }
}
