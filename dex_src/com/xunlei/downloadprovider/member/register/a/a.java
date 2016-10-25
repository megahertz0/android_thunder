package com.xunlei.downloadprovider.member.register.a;

import anet.channel.util.HttpConstant;
import com.alipay.sdk.util.h;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: CookiesParser.java
public final class a {
    public Map<String, List<String>> a;
    public Map<String, String> b;
    private final String c;

    public a() {
        this.c = "CookiesParser";
        this.b = new HashMap();
    }

    public final void a() {
        for (Entry entry : this.a.entrySet()) {
            if (HttpConstant.SET_COOKIE.equalsIgnoreCase((String) entry.getKey())) {
                for (String str : (List) entry.getValue()) {
                    String[] split = str.split(h.b);
                    int i = 0;
                    while (split != null && i < split.length) {
                        String[] split2 = split[i].split("=");
                        if (split2 != null) {
                            if (split2.length == 1) {
                                new StringBuilder("++[parse]cookie:").append(split2[0]).append("=");
                                this.b.put(split2[0], com.umeng.a.d);
                            } else if (split2.length == 2) {
                                new StringBuilder("++[parse]cookie:").append(split2[0]).append("=").append(split2[1]);
                                this.b.put(split2[0], split2[1]);
                            }
                        }
                        i++;
                    }
                }
            }
        }
    }
}
