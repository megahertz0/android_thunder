package com.xunlei.downloadprovider.loading.a;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// compiled from: InmobiADModel.java
public final class b {
    public String a;
    public String b;
    public String c;
    public String d;
    public List<String> e;
    public List<String> f;
    public List<String> g;
    int h;
    public boolean i;
    public int j;

    public b() {
        this.i = true;
    }

    public final List<String> a() {
        if (this.g != null) {
            Set<String> hashSet = new HashSet();
            for (String str : this.g) {
                if (str != null) {
                    hashSet.add(str.replace("$TS", String.valueOf(System.currentTimeMillis())));
                }
            }
            this.g.clear();
            for (String str2 : hashSet) {
                this.g.add(str2);
            }
        }
        return this.g;
    }
}
