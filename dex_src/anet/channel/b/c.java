package anet.channel.b;

import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// compiled from: Taobao
class c extends a {
    List<Long> c;
    HashMap<String, Long> d;

    c() {
        this.c = new LinkedList();
        this.d = new HashMap();
    }

    protected Object a(int i, Object... objArr) {
        if (i == 0) {
            a(((Integer) objArr[0]).intValue());
        } else if (i == 1) {
            a((String) objArr[0]);
            return b;
        }
        return a;
    }

    public void a(int i) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.FailOverHandler", "FailOverHandler hook onResponseCode", null, new Object[0]);
        }
        if (i < 500 || i >= 600) {
            synchronized (this.c) {
                this.c.clear();
            }
            return;
        }
        synchronized (this.c) {
            if (this.c.size() < 5) {
                this.c.add(Long.valueOf(System.currentTimeMillis()));
            } else {
                long longValue = ((Long) this.c.remove(0)).longValue();
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - longValue <= 60000) {
                    a(n.a());
                    this.c.clear();
                } else {
                    this.c.add(Long.valueOf(currentTimeMillis));
                }
            }
        }
    }

    private void a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.d) {
            Long l = (Long) this.d.get(str);
            if (l == null || l.longValue() - currentTimeMillis > 60000) {
                this.d.put(str, Long.valueOf(currentTimeMillis));
                StrategyCenter.getInstance().forceRefreshStrategy(str);
            }
        }
    }
}
