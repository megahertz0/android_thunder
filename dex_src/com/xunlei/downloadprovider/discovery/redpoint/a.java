package com.xunlei.downloadprovider.discovery.redpoint;

// compiled from: RedPointConfig.java
public final class a {
    public String a;
    public String b;
    public int c;
    public String d;
    public String e;
    public int f;
    public int g;
    public String h;
    public long i;
    public String j;
    public long k;
    public int l;
    public int m;
    public boolean n;
    public String o;
    public long p;

    public a() {
        this.c = -1;
    }

    public final boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis <= this.k && currentTimeMillis >= this.i && this.p == 0 && this.f == 1;
    }

    public final String toString() {
        return new StringBuilder("Test1 [mName= ").append(this.a).append(", mBusName=").append(this.b).append(", mRedPoint=").append(this.c).append(", mCofText=").append(this.d).append(", mPicUrl=").append(this.e).append(", mDisplay=").append(this.g).append(", mStatus=").append(this.f).append(", mJumpUrl= ").append(this.o).append(", mStartTime=").append(this.h).append(", mEndTime=").append(this.j).append("]").toString();
    }

    public final String b() {
        return this.a + this.b + this.c + this.d + this.e + this.h + this.j + this.f + this.g + this.f + this.o + this.l;
    }
}
