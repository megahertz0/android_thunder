package com.xunlei.downloadprovider.vod.protocol;

import com.xunlei.downloadprovider.vod.a.a;

// compiled from: VodProtocolBaseRequest.java
public final class i {
    public String a;
    public String b;
    public String c;
    public long d;
    public String e;
    public int f;
    public VodVideoFormat g;
    public VodSourceType h;
    public a i;

    public i() {
        this.f = 1;
        this.i = null;
    }

    public final String toString() {
        return new StringBuilder("title:").append(this.a).append("cid:").append(this.b).append("gcid:").append(this.c).append("url:").append(this.e).toString();
    }
}
