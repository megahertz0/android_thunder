package com.tencent.bugly.crashreport.crash;

// compiled from: BUGLY
public final class a implements Comparable<a> {
    public long a;
    public long b;
    public String c;
    public boolean d;
    public boolean e;
    public int f;

    public a() {
        this.a = -1;
        this.b = -1;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = 0;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        a aVar = (a) obj;
        if (aVar != null) {
            long j = this.b - aVar.b;
            if (j <= 0) {
                return j < 0 ? -1 : 0;
            }
        }
        return 1;
    }
}
