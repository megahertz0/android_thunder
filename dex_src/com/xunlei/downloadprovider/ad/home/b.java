package com.xunlei.downloadprovider.ad.home;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: OnlineCfg.java
public final class b {
    private static b f;
    public List<String> a;
    public Map<AD_TYPE, Integer> b;
    public Map<AD_TYPE, Integer> c;
    Map<AD_TYPE, Integer> d;
    public boolean e;

    private b() {
        this.a = new ArrayList();
        this.a.add("1116");
        this.a.add("1117");
        this.a.add("1136");
        this.b = new HashMap();
        this.b.put(AD_TYPE.SOURCE_GDT_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle));
        this.c = new HashMap();
        this.c.put(AD_TYPE.SOURCE_GDT_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle));
        this.d = new HashMap();
        this.d.put(AD_TYPE.SOURCE_GDT_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle));
    }

    public static b a() {
        if (f == null) {
            f = new b();
        }
        return f;
    }
}
