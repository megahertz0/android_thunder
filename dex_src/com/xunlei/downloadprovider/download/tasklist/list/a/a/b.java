package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import android.content.Context;
import java.util.List;

// compiled from: AbstractLoader.java
public abstract class b {
    protected Context a;
    protected int b;

    // compiled from: AbstractLoader.java
    static interface a {
        void a(int i);

        void a(List<com.xunlei.downloadprovider.download.tasklist.list.a.b.b> list);
    }

    public abstract void a(a aVar);

    public b(Context context, int i) {
        this.a = context;
        this.b = i;
    }
}
