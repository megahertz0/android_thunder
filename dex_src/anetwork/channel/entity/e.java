package anetwork.channel.entity;

import java.util.concurrent.ThreadFactory;

// compiled from: Taobao
final class e implements ThreadFactory {
    e() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, String.format("RepeaterThread:%d", new Object[]{Integer.valueOf(d.b.getAndIncrement())}));
    }
}
