package com.xunlei.thundersniffer.sniff.sniffer;

final class SniffingPageInfo {
    public String a;
    public String b;
    public String c;
    public String d;
    public int e;
    public int f;
    public boolean g;
    public String h;
    public int i;
    public String j;
    public String k;
    public int l;
    public boolean m;
    public int n;

    public static interface PageDataSource {
        public static final int BROWSER = 1;
        public static final int GETHTML = 2;
        public static final int NONE = 0;
    }

    SniffingPageInfo(String str) {
        this.e = -1;
        this.f = 0;
        this.g = false;
        this.i = 0;
        this.l = 0;
        this.m = false;
        this.n = 0;
        this.a = str;
        this.b = str;
    }
}
