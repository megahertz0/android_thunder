package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d.b.b.a;
import java.util.Map;

// compiled from: DownloadADConfig.java
public final class g implements a {
    final /* synthetic */ f a;

    public g(f fVar) {
        this.a = fVar;
    }

    public final void a(Map<String, Map<AD_TYPE, Integer>> map, String str) {
        this.a.g = true;
        this.a.f = false;
        if (map.get("1175") != null) {
            this.a.b = (Map) map.get("1175");
        }
        if (map.get("1177") != null) {
            this.a.d = (Map) map.get("1177");
        }
        if (map.get("1176") != null) {
            this.a.c = (Map) map.get("1176");
        }
        for (f.a aVar : this.a.e) {
            aVar.a();
        }
    }

    public final void a() {
        this.a.f = false;
        this.a.g = false;
        for (f.a aVar : this.a.e) {
            aVar.b();
        }
    }
}
