package com.xunlei.downloadprovider.b.c;

import anet.channel.util.HttpConstant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: BpDataLoader.java
public final class a extends e {
    public static final int DATA_LOADER_PARSE_ERROR = 1000;
    public static final int DATA_TOO_LARGE_ERROR = 999;
    protected int a;
    private a b;
    private g c;
    public i mParser;
    public Object mUserData;

    // compiled from: BpDataLoader.java
    public static interface a {
        void a(int i, Object obj, Map<String, List<String>> map);
    }

    public a(String str, String str2, String str3, i iVar) {
        this(str, str2, str3, null, null, null, iVar, false, 0);
    }

    public a(String str, String str2, String str3, String str4, i iVar) {
        this(str, str2, str3, str4, null, null, iVar, false, 0);
    }

    public a(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, i iVar) {
        this(str, str2, str3, str4, (HashMap) hashMap, null, iVar, false, 0);
    }

    public a(String str, String str2, String str3, i iVar, boolean z) {
        this(str, str2, str3, null, null, null, iVar, z, 0);
    }

    public a(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, List<byte[]> list, i iVar, boolean z, int i) {
        this.a = 0;
        this.mParser = iVar;
        this.c = new g(str);
        this.c.g = i;
        this.c.d = new b(this);
        this.c.b = new c(this);
        this.c.a = new d(this);
        this.c.a(str2, str3, str4, hashMap, list, z);
    }

    public a(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, i iVar, int i, int i2) {
        this(str, str2, str3, str4, (HashMap) hashMap, null, iVar, false, 0);
        this.c.a(i, i2);
    }

    public a(String str, String str2, String str3, List<byte[]> list, String str4, HashMap<String, String> hashMap, i iVar, int i, int i2) {
        this(str, str2, str3, str4, (HashMap) hashMap, (List) list, iVar, false, 0);
        this.c.a(i, i2);
    }

    public a(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, i iVar, int i, int i2, int i3) {
        this(str, str2, str3, str4, (HashMap) hashMap, null, iVar, false, i3);
        this.c.a(i, i2);
    }

    public final void run() {
        if (this.c != null) {
            this.c.run();
        }
    }

    public final void cancel() {
        if (this.c != null) {
            this.c.cancel();
            super.cancel();
        }
    }

    public final void setBpOnDataLoaderCompleteListener(a aVar) {
        if (aVar != null) {
            this.b = aVar;
        }
    }

    public final void setGzip(boolean z) {
        g gVar = this.c;
        gVar.f = z;
        if (gVar.f) {
            if (gVar.e == null) {
                gVar.e = new HashMap();
            }
            gVar.e.put(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
        }
    }
}
