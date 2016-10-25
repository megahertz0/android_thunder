package com.xunlei.downloadprovider.personal.lixianspace.widget;

import com.handmark.pulltorefresh.library.PullToRefreshBase.a;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceFragment$OntainState;

// compiled from: LixianSpaceListWidget.java
final class e implements a {
    final /* synthetic */ LixianSpaceListWidget a;

    e(LixianSpaceListWidget lixianSpaceListWidget) {
        this.a = lixianSpaceListWidget;
    }

    public final void a() {
        if (!this.a.e) {
            if (!LixianSpaceListWidget.d(this.a) && LixianSpaceListWidget.e(this.a) == LixianSpaceFragment$OntainState.idle) {
                LixianSpaceListWidget.a(this.a, LixianSpaceFragment$OntainState.obtaining);
            } else if (LixianSpaceListWidget.d(this.a)) {
                LixianSpaceListWidget.f(this.a).setFooterNeedShow(false);
            }
        }
    }
}
