package com.xunlei.thundersniffer.sniff.sniffer;

import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.misc.ResourceOperationMonitor;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;

final class at {
    public int a;
    public int b;
    public int c;
    public int d;
    public String e;
    public String f;
    public String g;
    public int h;
    public ArrayList<SniffingResource> i;
    SniffingResourceGroup j;
    public int k;
    public int l;
    public boolean m;
    public String n;
    public int o;
    public int p;
    public String q;
    public String r;
    public String s;
    ResourceOperationMonitor t;

    at() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = BuildConfig.VERSION_NAME;
        this.f = BuildConfig.VERSION_NAME;
        this.g = BuildConfig.VERSION_NAME;
        this.h = -1;
        this.i = null;
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.n = null;
        this.o = 0;
        this.t = new ResourceOperationMonitor();
    }
}
