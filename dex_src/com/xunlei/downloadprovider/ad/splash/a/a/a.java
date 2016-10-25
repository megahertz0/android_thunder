package com.xunlei.downloadprovider.ad.splash.a.a;

import com.xunlei.downloadprovider.download.tasklist.list.e.b.b;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: BaseAdInfo.java
public abstract class a<T> implements f, b {
    public T b;
    protected int c;

    public a(T t) {
        this.b = null;
        this.b = t;
    }

    public int e() {
        return g() ? SimpleLog.LOG_LEVEL_DEBUG : 1;
    }

    public int c_() {
        return this.c;
    }

    public final void a(int i) {
        this.c = i;
    }

    public String toString() {
        return new StringBuilder(" positionId: ").append(c_()).append(" title: ").append(h()).append(" desc: ").append(i()).append(" imageUrl: ").append(j()).toString();
    }
}
