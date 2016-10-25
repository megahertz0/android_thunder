package com.xunlei.downloadprovider.ad.home.a;

import com.xunlei.downloadprovider.ad.common.c.a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: LoadADClient.java
public final class d implements a {
    final /* synthetic */ c a;

    public d(c cVar) {
        this.a = cVar;
    }

    public final void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        if (list != null && list.size() != 0) {
            this.a.c.e = (com.xunlei.downloadprovider.ad.common.a) list.get(0);
        }
    }

    public final void a(int i, String str) {
        Map hashMap = new HashMap();
        hashMap.put("errorcode", String.valueOf(i));
        com.xunlei.downloadprovider.ad.home.a.a("adv_homeflow_first_fail", hashMap, true);
    }
}
