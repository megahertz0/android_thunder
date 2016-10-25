package com.xunlei.downloadprovider.vod.protocol;

import java.io.Serializable;
import java.util.List;

// compiled from: EpisodeInfo.java
public final class h implements Serializable {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public long f;
    public String g;
    public boolean h;
    public boolean i;
    public boolean j;
    public String k;
    public String l;
    public String m;
    public String n;
    public int o;
    public String p;
    public String q;
    public int r;
    public int s;
    public List<b> t;
    public boolean u;
    public int v;
    public List<a> w;
    public VodVideoFormat x;

    // compiled from: EpisodeInfo.java
    public static class a implements Serializable {
        public String a;
        public String b;
        public String c;
        public String d;
    }

    // compiled from: EpisodeInfo.java
    public static class b implements Serializable {
        public String a;
        public int b;
    }

    public h() {
        this.h = false;
        this.i = false;
        this.j = false;
        this.t = null;
        this.u = true;
        this.v = 1;
        this.w = null;
    }
}
