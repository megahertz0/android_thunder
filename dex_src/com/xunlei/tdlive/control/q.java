package com.xunlei.tdlive.control;

import android.database.DataSetObserver;

// compiled from: RecycleableViewPager.java
class q extends DataSetObserver {
    final /* synthetic */ a a;

    q(a aVar) {
        this.a = aVar;
    }

    public void onChanged() {
        this.a.notifyDataSetChanged();
    }

    public void onInvalidated() {
        this.a.notifyDataSetChanged();
    }
}
