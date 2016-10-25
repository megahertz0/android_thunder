package com.xunlei.downloadprovider.ad.common.d.b;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d.c;
import java.util.List;
import java.util.Map;

// compiled from: SSPCfgLoader.java
public final class b {
    private static b a;

    // compiled from: SSPCfgLoader.java
    public static interface a {
        void a();

        void a(Map<String, Map<AD_TYPE, Integer>> map, String str);
    }

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public final void a(List<String> list, a aVar) {
        com.xunlei.downloadprovider.j.a.a().e().a(new c(new com.xunlei.downloadprovider.ad.common.d.a.b(list), new c(this, aVar), new d(this, aVar)));
    }
}
