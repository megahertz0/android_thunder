package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.strategy.k.b;
import anet.channel.strategy.k.c;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
class HorseRideStrategyMap implements Serializable {
    private Map<String, HorseRideStrategy> a;

    HorseRideStrategyMap() {
        a();
    }

    void a() {
        if (this.a == null) {
            this.a = new HashMap();
        }
    }

    void a(c cVar) {
        if (cVar.c != null) {
            synchronized (this.a) {
                for (int i = 0; i < cVar.c.length; i++) {
                    b bVar = cVar.c[i];
                    if (bVar.m) {
                        this.a.remove(bVar.a);
                    } else if (bVar.o) {
                        continue;
                    } else if (TextUtils.isEmpty(bVar.g)) {
                        this.a.remove(bVar.a);
                    } else {
                        this.a.put(bVar.a, a.a(bVar.g, bVar.i, (long) bVar.h, bVar.k, bVar.j));
                    }
                }
            }
        }
    }

    Map<String, IHRStrategy> a(StrategyTable strategyTable) {
        Map<String, IHRStrategy> map;
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                map = Collections.EMPTY_MAP;
            } else {
                strategyTable.fillLastHorseRideTime(this.a);
                map = new HashMap(this.a);
            }
        }
        return map;
    }
}
