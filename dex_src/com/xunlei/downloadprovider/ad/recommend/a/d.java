package com.xunlei.downloadprovider.ad.recommend.a;

import android.text.TextUtils;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d.b.a.a;
import com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.xllib.R;
import java.util.HashMap;
import java.util.Map;

// compiled from: RecommendAdCfg.java
public final class d {
    public static d a;
    Map<String, Map<AD_TYPE, Integer>> b;

    private d() {
        this.b = new HashMap(9);
        Object b = aa.b(BrothersApplication.a(), "recommend_use_ad_ssp_config");
        if (TextUtils.isEmpty(b)) {
            b();
            return;
        }
        a a = com.xunlei.downloadprovider.ad.common.d.b.a.a(b);
        if (a.a == 0) {
            a(a.b);
        }
    }

    public static d a() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    final void a(Map<String, Map<AD_TYPE, Integer>> map) {
        if (map != null) {
            this.b.putAll(map);
        }
    }

    private void b() {
        this.b.clear();
        for (String str : RecommendSSPAdMapping.getAllPositionIds()) {
            a(str);
        }
    }

    final void a(String str) {
        Map map = this.b;
        Map hashMap = new HashMap(2);
        hashMap.put(AD_TYPE.SOURCE_GDT_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_buttonBarStyle));
        hashMap.put(AD_TYPE.SOURCE_BAIDU_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_buttonBarStyle));
        map.put(str, hashMap);
    }
}
