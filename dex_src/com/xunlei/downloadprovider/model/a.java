package com.xunlei.downloadprovider.model;

@Deprecated
// compiled from: AdditionInfoController.java
public final class a {
    private static a b;
    public p a;

    @Deprecated
    // compiled from: AdditionInfoController.java
    public static class a {
        public long a;
        public int b;
        public long c;
        public int d;
        public long e;
        public long f;
        public long g;
        public int h;
        public int i;
        public String j;
        public String k;
        public String l;
        public String m;
        public int n;
        public int o;
        public String p;
        public int q;
        public int r;

        public a() {
            this.n = 1;
            this.o = 256;
            this.p = null;
            this.q = 0;
            this.r = 0;
        }

        public a(long j, int i, long j2, int i2, long j3, long j4, int i3, String str) {
            this.n = 1;
            this.o = 256;
            this.p = null;
            this.q = 0;
            this.r = 0;
            this.a = j;
            this.b = i;
            this.c = j2;
            this.d = i2;
            this.e = j3;
            this.f = j4;
            this.i = i3;
            this.j = str;
        }

        public final String toString() {
            return new StringBuilder(" [mTaskId").append(this.a).append(" mMode:").append(this.b).append(" mOrginSize:").append(this.c).append(" mMaxSpeed:").append(this.d).append(" mFileSize:").append(this.e).append(" mTotaltime:").append(this.f).append("] ").toString();
        }
    }

    static {
        b = null;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    private a() {
        this.a = null;
        this.a = p.a();
    }
}
