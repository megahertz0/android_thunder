package com.xunlei.downloadprovider.ad.home;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d.b.b.a;
import java.util.Map;

// compiled from: OnlineCfg.java
public final class c implements a {
    final /* synthetic */ b a;

    public c(b bVar) {
        this.a = bVar;
    }

    public final void a(Map<String, Map<AD_TYPE, Integer>> map, String str) {
        if (map.get("1116") != null) {
            this.a.b = (Map) map.get("1116");
        }
        if (map.get("1117") != null) {
            this.a.c = (Map) map.get("1117");
        }
        if (map.get("1136") != null) {
            this.a.d = (Map) map.get("1136");
        }
        this.a.e = false;
    }

    public final void a() {
        this.a.e = false;
    }
}
