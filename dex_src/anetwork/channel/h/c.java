package anetwork.channel.h;

import java.util.concurrent.atomic.AtomicInteger;

// compiled from: Taobao
public final class c {
    private static AtomicInteger a;

    static {
        a = new AtomicInteger(0);
    }

    public static String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder(16);
        if (str != null) {
            stringBuilder.append(str).append('.');
        }
        stringBuilder.append(str2).append(a.incrementAndGet() & Integer.MAX_VALUE);
        return stringBuilder.toString();
    }
}
