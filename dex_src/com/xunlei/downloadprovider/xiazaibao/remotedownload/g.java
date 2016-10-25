package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.support.v7.widget.RecyclerView.t;
import android.view.View;

// compiled from: RecyclerViewHolder.java
public abstract class g extends t {
    d b;
    boolean c;
    q d;
    a e;

    // compiled from: RecyclerViewHolder.java
    public static interface a {
        void a();

        void a(int i, boolean z);
    }

    public abstract void a(al alVar);

    public g(View view) {
        super(view);
    }

    public void a(boolean z) {
        this.c = z;
    }
}
