package anet.channel.strategy.dispatch;

import anet.channel.c.c;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.util.ALog;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
class e {
    public static final int REQUEST_MERGE_PERIOD = 2500;
    Map<String, Object> a;

    // compiled from: Taobao
    private static class a implements Runnable {
        private Map<String, Object> a;
        private NetworkStatus b;

        a(Map<String, Object> map) {
            this.a = map;
            this.b = NetworkStatusHelper.a();
        }

        public void run() {
            try {
                if (NetworkStatusHelper.e() || NetworkStatusHelper.a() == this.b) {
                    b.b(c.a(this.a));
                }
            } catch (Throwable e) {
                ALog.e("DispatchTaskExecutor", "exec amdc task failed.", null, e, new Object[0]);
            }
        }
    }

    e() {
    }

    public void a(Map<String, Object> map) {
        synchronized (this) {
            if (this.a == null) {
                this.a = map;
                c.a(new f(this), 2500, TimeUnit.MILLISECONDS);
            } else {
                Set set = (Set) this.a.get(a.HOSTS);
                Set set2 = (Set) map.get(a.HOSTS);
                if (set.size() + set2.size() >= 40) {
                    c.a(new a(map), 0);
                    return;
                }
                set2.addAll(set);
                this.a = map;
            }
        }
    }
}
