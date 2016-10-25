package anetwork.channel.f;

import anet.channel.util.StringUtils;
import java.io.Serializable;

// compiled from: Taobao
public final class a implements Serializable, Cloneable {
    @Deprecated
    public long A;
    public long B;
    @Deprecated
    public String C;
    public int D;
    public String E;
    public String a;
    public boolean b;
    public int c;
    public String d;
    @Deprecated
    public String e;
    public String f;
    @Deprecated
    public boolean g;
    public boolean h;
    @Deprecated
    public int i;
    @Deprecated
    public int j;
    @Deprecated
    public long k;
    @Deprecated
    public long l;
    public long m;
    @Deprecated
    public long n;
    @Deprecated
    public long o;
    public long p;
    @Deprecated
    public long q;
    public long r;
    public long s;
    public long t;
    @Deprecated
    public long u;
    public long v;
    public long w;
    @Deprecated
    public long x;
    public long y;
    public long z;

    public a() {
        this.a = com.umeng.a.d;
        this.b = false;
        this.c = 0;
        this.d = com.umeng.a.d;
        this.e = com.umeng.a.d;
        this.f = com.umeng.a.d;
        this.g = false;
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = com.umeng.a.d;
    }

    public final String toString() {
        StringBuilder stringBuilder;
        if (StringUtils.isBlank(this.E)) {
            stringBuilder = new StringBuilder(128);
            stringBuilder.append("isRequestSuccess=").append(this.b);
            stringBuilder.append(",host=").append(this.d);
            stringBuilder.append(",ip_port=").append(this.f);
            stringBuilder.append(",isSSL=").append(this.h);
            stringBuilder.append(",connType=").append(this.a);
            stringBuilder.append(",oneWayTime_ANet=").append(this.m);
            stringBuilder.append(",postBodyTime=").append(this.p);
            stringBuilder.append(",firstDataTime=").append(this.s);
            stringBuilder.append(",recDataTime=").append(this.t);
            stringBuilder.append(",serverRT=").append(this.v);
            stringBuilder.append(",rtt=").append(this.w);
            stringBuilder.append(",sendSize=").append(this.y);
            stringBuilder.append(",totalSize=").append(this.z);
            stringBuilder.append(",dataSpeed=").append(this.B);
            stringBuilder.append(",retryTime=").append(this.D);
            this.E = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder("StatisticData [");
        stringBuilder.append(this.E);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
