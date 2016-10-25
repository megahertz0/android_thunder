package com.xunlei.downloadprovider.ad.splash.b;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d.b.b.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.util.aa;
import java.util.Map;

// compiled from: SplashAdConfig.java
public final class j implements a {
    final /* synthetic */ i a;

    public j(i iVar) {
        this.a = iVar;
    }

    public final void a() {
    }

    public final void a(Map<String, Map<AD_TYPE, Integer>> map, String str) {
        aa.a(BrothersApplication.a(), "splash_ad_ssp_config", str);
        if (map != null && map.get("1115") != null) {
            this.a.a.clear();
            this.a.a.putAll((Map) map.get("1115"));
        }
    }
}
