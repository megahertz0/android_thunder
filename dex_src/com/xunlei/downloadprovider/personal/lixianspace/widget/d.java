package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceFragment$OntainState;

// compiled from: LixianSpaceListWidget.java
final class d implements e<ListView> {
    final /* synthetic */ LixianSpaceListWidget a;

    d(LixianSpaceListWidget lixianSpaceListWidget) {
        this.a = lixianSpaceListWidget;
    }

    public final void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        LixianSpaceListWidget.c(this.a);
        if (!this.a.e) {
            if (this.a.f) {
                LixianSpaceListWidget.a(this.a, LixianSpaceFragment$OntainState.refreshing);
            } else {
                this.a.a(null);
            }
        }
    }

    public final void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
    }
}
