package com.xunlei.downloadprovider.ad.common.d;

import com.umeng.common.a;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.a.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: AbstractRequestParam.java
public class a$c extends a {
    private List<String> a;

    public a$c(List<String> list) {
        this.a = null;
        this.a = list;
    }

    protected final Map<String, String> c() {
        Map hashMap = new HashMap();
        hashMap.put("positionId", a.a(this.a));
        hashMap.put("isAll", c.f);
        hashMap.put("versionCode", b.x());
        hashMap.put(a.d, b.g());
        return hashMap;
    }

    protected final String d() {
        return "http://api.tw06.xlmc.sandai.net/api/adp/get";
    }
}
