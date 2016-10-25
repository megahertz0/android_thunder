package com.xunlei.downloadprovider.ad.home.a;

import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// compiled from: LoadADClient.java
public class c$a {
    Map<AD_LAYOUT_TYPE, a> a;
    public Set<String> b;
    public Set<String> c;
    public a d;
    public a e;
    public boolean f;
    final /* synthetic */ c g;
    private Set<String> h;
    private Map<String, a> i;

    c$a(c cVar) {
        this.g = cVar;
        this.h = new HashSet();
        this.b = new HashSet();
        this.i = new HashMap();
        this.c = new HashSet();
        this.a = new HashMap();
    }

    public final a a(String str) {
        return this.i != null ? (a) this.i.get(str) : null;
    }

    public final void a(String str, a aVar) {
        if (this.i != null) {
            this.i.put(str, aVar);
        }
    }
}
