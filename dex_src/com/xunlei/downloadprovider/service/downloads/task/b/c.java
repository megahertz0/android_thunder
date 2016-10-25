package com.xunlei.downloadprovider.service.downloads.task.b;

import com.umeng.a;
import java.io.Serializable;

// compiled from: TaskCountInfo.java
public final class c implements Serializable {
    public long a;
    public String b;
    public long c;
    public int d;
    public String e;
    public long f;
    public long g;

    public c() {
        this.a = 0;
        this.b = a.d;
        this.c = 0;
        this.d = 0;
        this.e = a.d;
        this.f = 0;
        this.g = 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.g == ((c) obj).g;
    }

    public final int hashCode() {
        return (int) (this.g ^ (this.g >>> 32));
    }
}
