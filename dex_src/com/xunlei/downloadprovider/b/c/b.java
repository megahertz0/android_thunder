package com.xunlei.downloadprovider.b.c;

import com.umeng.message.util.HttpRequest;
import com.xunlei.downloadprovider.b.c.g.a;
import java.util.List;
import java.util.Map;

// compiled from: BpDataLoader.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Map<String, List<String>> map, g gVar) {
        if (200 == i && map != null) {
            List list = (List) map.get(HttpRequest.k);
            if (list != null && list.size() > 0) {
                try {
                    if (Integer.parseInt((String) list.get(0)) >= 2097152) {
                        this.a.a = 999;
                        if (a.a(this.a) != null) {
                            a.a(this.a).a(this.a.a, null, null);
                            a.b(this.a);
                        }
                        this.a.cancel();
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
