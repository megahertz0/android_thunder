package com.xunlei.downloadprovider.ad.recommend.a;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d.b.b.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.util.aa;
import java.util.Map;

// compiled from: RecommendAdCfg.java
public final class e implements a {
    final /* synthetic */ d a;

    public e(d dVar) {
        this.a = dVar;
    }

    public final void a() {
    }

    public final void a(Map<String, Map<AD_TYPE, Integer>> map, String str) {
        aa.a(BrothersApplication.a(), "recommend_use_ad_ssp_config", str);
        this.a.a((Map) map);
    }
}
