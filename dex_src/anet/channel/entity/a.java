package anet.channel.entity;

import anet.channel.strategy.IConnStrategy;

// compiled from: Taobao
public class a {
    public final IConnStrategy a;
    public int b;
    public int c;
    private String d;
    private String e;

    public a(String str, String str2, IConnStrategy iConnStrategy) {
        this.b = 0;
        this.c = 0;
        this.a = iConnStrategy;
        this.d = str;
        this.e = str2;
    }

    public String a() {
        return this.a != null ? this.a.getIp() : null;
    }

    public int b() {
        return this.a != null ? this.a.getPort() : 0;
    }

    public ConnType c() {
        return this.a != null ? this.a.getConnType() : ConnType.HTTP;
    }

    public int d() {
        return (this.a == null || this.a.getConnectionTimeout() == 0) ? com.alipay.sdk.data.a.d : this.a.getConnectionTimeout();
    }

    public int e() {
        return (this.a == null || this.a.getReadTimeout() == 0) ? com.alipay.sdk.data.a.d : this.a.getReadTimeout();
    }

    public boolean f() {
        return this.a != null ? this.a.isNeedAuth() : false;
    }

    public String g() {
        return this.d;
    }

    public int h() {
        return this.a != null ? this.a.getHeartbeat() : 45000;
    }

    public String i() {
        return this.e;
    }

    public String toString() {
        return new StringBuilder("ConnInfo [ip=").append(a()).append(",port=").append(b()).append(",type=").append(c()).append(",hb").append(h()).append("]").toString();
    }
}
