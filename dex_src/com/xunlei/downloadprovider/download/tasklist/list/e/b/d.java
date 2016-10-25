package com.xunlei.downloadprovider.download.tasklist.list.e.b;

import android.view.View;
import com.xunlei.downloadprovider.download.tasklist.list.e.a.a;

// compiled from: RecommendAdInfo.java
public abstract class d<T> implements a, b, c {
    protected T a;

    public abstract void onClick(View view);

    public final T a() {
        return this.a;
    }

    public String toString() {
        return new StringBuilder("source: ").append(this.a.toString()).append(" isApp: ").append(b()).append(" title: ").append(c()).append(" desc: ").append(e()).append(" imgUrl: ").append(f()).append(" iconUrl: ").append(g()).append(" pageIndex: ").append(a.b(String.valueOf(c_()))).append(" position: ").append(a.a(String.valueOf(c_()))).append(" positionId: ").append(c_()).toString();
    }
}
