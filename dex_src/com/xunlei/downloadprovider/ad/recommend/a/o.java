package com.xunlei.downloadprovider.ad.recommend.a;

import android.database.Observable;
import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// compiled from: RecommendAdObservable.java
public class o extends Observable<Map<Integer, a>> {
    private static final String a;

    static {
        a = o.class.getSimpleName();
    }

    public final void a(int i, List<com.xunlei.downloadprovider.ad.common.a> list) {
        new StringBuilder("onloadSuccess: ").append(Arrays.toString(list.toArray()));
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (map.containsKey(Integer.valueOf(i))) {
                ((a) map.get(Integer.valueOf(i))).a(list);
            }
        }
    }

    public final void a(int i, int i2, String str) {
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (map.containsKey(Integer.valueOf(i))) {
                ((a) map.get(Integer.valueOf(i))).a(i2, str);
            }
        }
    }
}
