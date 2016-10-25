package com.xunlei.downloadprovider.ad.splash.b;

import android.text.TextUtils;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.d.b.a.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.tdlive.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: SplashAdConfig.java
public final class i {
    private static i b;
    Map<AD_TYPE, Integer> a;

    private i() {
        this.a = new HashMap();
        Object b = aa.b(BrothersApplication.a(), "splash_ad_ssp_config");
        if (TextUtils.isEmpty(b)) {
            c();
            return;
        }
        a a = com.xunlei.downloadprovider.ad.common.d.b.a.a(b);
        if (a.a == 0 && a.b != null && a.b.get("1115") != null) {
            this.a.clear();
            this.a.putAll((Map) a.b.get("1115"));
        }
    }

    public static i a() {
        if (b == null) {
            b = new i();
        }
        return b;
    }

    public final Map<AD_TYPE, Integer> b() {
        if (this.a.isEmpty()) {
            c();
        }
        for (Entry entry : this.a.entrySet()) {
            new StringBuilder("key: ").append(((AD_TYPE) entry.getKey()).name()).append(" value: ").append(entry.getValue());
        }
        return this.a;
    }

    private void c() {
        this.a.clear();
        this.a.put(AD_TYPE.SOURCE_GDT_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_buttonBarStyle));
        this.a.put(AD_TYPE.SOURCE_BAIDU_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_buttonBarStyle));
    }
}
