package com.xunlei.tdlive;

import android.widget.ListView;
import com.xunlei.tdlive.a.j.a;

// compiled from: SDKLiveListFragment.java
class eb implements a {
    final /* synthetic */ ea a;

    eb(ea eaVar) {
        this.a = eaVar;
    }

    public <T> void a(T t, boolean z, boolean z2) {
        if (z) {
            ((ListView) ea.b(this.a).getRefreshableView()).removeHeaderView(ea.a(this.a));
            ((ListView) ea.b(this.a).getRefreshableView()).removeHeaderView(ea.c(this.a));
            if (ea.d(this.a).getCount() > 0) {
                ((ListView) ea.b(this.a).getRefreshableView()).addHeaderView(ea.a(this.a));
            }
            ((ListView) ea.b(this.a).getRefreshableView()).addHeaderView(ea.c(this.a));
        }
    }
}
