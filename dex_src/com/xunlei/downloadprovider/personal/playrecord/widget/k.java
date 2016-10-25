package com.xunlei.downloadprovider.personal.playrecord.widget;

import com.handmark.pulltorefresh.library.PullToRefreshBase.a;
import com.xunlei.downloadprovider.personal.playrecord.PlayRecordFragment$OntainState;

// compiled from: PlayRecordListWidget.java
final class k implements a {
    final /* synthetic */ PlayRecordListWidget a;

    k(PlayRecordListWidget playRecordListWidget) {
        this.a = playRecordListWidget;
    }

    public final void a() {
        PlayRecordListWidget.w(this.a);
        if (!this.a.e) {
            if (!PlayRecordListWidget.j(this.a) && PlayRecordListWidget.x(this.a) == PlayRecordFragment$OntainState.idle) {
                PlayRecordListWidget.v(this.a);
            } else if (PlayRecordListWidget.j(this.a)) {
                PlayRecordListWidget.b(this.a, PlayRecordListWidget.j(this.a));
            }
        }
    }
}
