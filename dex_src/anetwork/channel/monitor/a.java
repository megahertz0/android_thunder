package anetwork.channel.monitor;

import anet.channel.monitor.NetworkSpeed;
import anet.channel.util.ALog;
import java.util.concurrent.atomic.AtomicBoolean;

// compiled from: Taobao
public final class a {
    static AtomicBoolean a;

    static {
        a = new AtomicBoolean(false);
    }

    public static synchronized void a() {
        synchronized (a.class) {
            if (a.compareAndSet(false, true)) {
                anet.channel.monitor.a.a().c();
            }
        }
    }

    public static NetworkSpeed b() {
        NetworkSpeed networkSpeed = NetworkSpeed.Fast;
        try {
            return NetworkSpeed.valueOfCode(anet.channel.monitor.a.a().b());
        } catch (Throwable th) {
            ALog.e("ANet.Monitor", "getNetworkSpeed failed", null, th, new Object[0]);
            return networkSpeed;
        }
    }
}
