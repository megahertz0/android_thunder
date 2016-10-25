package com.inmobi.ads;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.info.a;
import com.inmobi.commons.core.utilities.uid.d;
import com.inmobi.signals.LocationInfo;
import com.inmobi.signals.a.c;
import com.inmobi.signals.b.b;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: AdNetworkRequest.java
public final class e extends NetworkRequest {
    private long d;
    private String e;
    private String f;
    private int g;
    private String h;
    private String i;
    private Map<String, String> j;
    private Map<String, String> k;

    public e(String str, long j, d dVar) {
        super(RequestType.POST, str, true, dVar, true);
        this.e = "json";
        this.g = 1;
        this.d = j;
        this.c.put("im-plid", String.valueOf(this.d));
        this.c.putAll(com.inmobi.commons.core.utilities.info.e.g());
        this.c.putAll(DisplayInfo.c());
        this.c.put("u-appIS", a.a().b());
        this.c.putAll(LocationInfo.a().g());
        this.c.putAll(LocationInfo.a().f());
        this.c.putAll(b.b());
        this.c.putAll(b.d());
        this.c.putAll(c.c());
        this.c.putAll(c.d());
        this.c.putAll(c.a());
    }

    public final void a() {
        super.a();
        this.c.put("format", this.e);
        this.c.put("mk-ads", String.valueOf(this.g));
        this.c.put("adtype", this.h);
        if (this.i != null) {
            this.c.put("p-keywords", this.i);
        }
        if (this.j != null) {
            for (Entry entry : this.j.entrySet()) {
                if (!this.c.containsKey(entry.getKey())) {
                    this.c.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.k != null) {
            this.c.putAll(this.k);
        }
    }

    public final void a(String str) {
        this.h = str;
    }

    public final String b() {
        return this.h;
    }

    public final void b(String str) {
        this.e = str;
    }

    public final String c() {
        return this.f;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final void a(int i) {
        this.g = i;
    }

    public final int d() {
        return this.g;
    }

    public final long e() {
        return this.d;
    }

    public final void d(String str) {
        this.i = str;
    }

    public final void a(Map<String, String> map) {
        this.j = map;
    }

    public final void b(Map<String, String> map) {
        this.k = map;
    }
}
