package anet.channel.util;

import java.util.concurrent.atomic.AtomicInteger;

// compiled from: Taobao
public class i {
    private static AtomicInteger a;

    static {
        a = new AtomicInteger();
    }

    public static String a() {
        if (a.get() == Integer.MAX_VALUE) {
            a.set(0);
        }
        return StringUtils.buildString("AWCN", String.valueOf(a.incrementAndGet()));
    }
}
