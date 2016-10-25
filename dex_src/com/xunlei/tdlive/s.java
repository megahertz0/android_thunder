package com.xunlei.tdlive;

import android.widget.ListView;
import com.xunlei.tdlive.a.j.a;

// compiled from: LiveListFragment.java
class s implements a {
    final /* synthetic */ q a;

    s(q qVar) {
        this.a = qVar;
    }

    public <T> void a(T t, boolean z, boolean z2) {
        if (z) {
            ((ListView) q.b(this.a).getRefreshableView()).removeHeaderView(q.a(this.a));
            if (q.c(this.a).getCount() > 0) {
                ((ListView) q.b(this.a).getRefreshableView()).addHeaderView(q.a(this.a));
            }
        }
    }
}
