package com.xunlei.downloadprovider.homepage.relax;

import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: RelaxListFragment.java
final class d implements e<ListView> {
    final /* synthetic */ RelaxListFragment a;

    d(RelaxListFragment relaxListFragment) {
        this.a = relaxListFragment;
    }

    public final void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        RelaxListFragment.c(this.a);
        RelaxListFragment.d(this.a);
        StatReporter.reportRelaxRefresh(0, GuestureType.TOP);
    }

    public final void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        RelaxListFragment.c(this.a);
        RelaxListFragment.e(this.a);
        StatReporter.reportRelaxRefresh(0, GuestureType.BOTTOM);
    }
}
