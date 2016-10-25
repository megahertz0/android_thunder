package anetwork.channel.http;

import android.content.Context;
import anet.channel.util.ALog;
import anetwork.channel.c.a;
import java.util.concurrent.atomic.AtomicBoolean;

// compiled from: Taobao
public final class b {
    static AtomicBoolean a;
    public static Context b;

    static {
        a = new AtomicBoolean(false);
    }

    public static void a(Context context) {
        if (context != null) {
            try {
                if (a.compareAndSet(false, true)) {
                    b = context;
                    a.a(context);
                    anetwork.channel.monitor.a.a();
                    anetwork.channel.b.b.a();
                }
            } catch (Throwable th) {
                ALog.w("ANet.NetworkSdkSetting", "[init]", null, th, new Object[0]);
            }
        }
    }
}
