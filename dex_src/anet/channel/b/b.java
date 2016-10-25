package anet.channel.b;

import anet.channel.util.ALog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

// compiled from: Taobao
public class b {
    private static final Class<? extends a>[] c;
    Map<Class, a> a;
    CopyOnWriteArrayList<a> b;

    // compiled from: Taobao
    private static class a {
        static b a;

        private a() {
        }

        static {
            a = new b();
        }
    }

    static {
        c = new Class[]{c.class};
    }

    private b() {
        this.a = new HashMap();
        this.b = new CopyOnWriteArrayList();
        b();
    }

    private void b() {
        for (int i = 0; i < c.length; i++) {
            try {
                this.a.put(c[i], c[i].newInstance());
            } catch (Throwable e) {
                ALog.e("awcn.EventHandlerManager", "instantiate plugin failed.", null, e, new Object[0]);
            }
        }
        this.b.addAll(this.a.values());
    }

    public <T> T a(int i, Object... objArr) {
        T a;
        Iterator it = this.b.iterator();
        Object obj = null;
        while (it.hasNext()) {
            a = ((a) it.next()).a(i, objArr);
            if (a != a.a) {
                break;
            }
        }
        return (a == a.a || a == a.b) ? null : a;
    }

    public static b a() {
        return a.a;
    }
}
