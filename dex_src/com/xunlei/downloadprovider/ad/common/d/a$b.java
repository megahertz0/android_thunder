package com.xunlei.downloadprovider.ad.common.d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: AbstractRequestParam.java
public class a$b extends a {
    private List<String> a;

    public a$b(List<String> list) {
        this.a = null;
        this.a = list;
    }

    protected final Map<String, String> c() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("positionIds", a.a(this.a));
        return hashMap;
    }

    protected final String d() {
        return "http://api.tw06.xlmc.sandai.net/api/adp/getPosition";
    }
}
