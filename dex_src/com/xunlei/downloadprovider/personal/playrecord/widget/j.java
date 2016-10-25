package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;

// compiled from: PlayRecordListWidget.java
final class j implements e<ListView> {
    final /* synthetic */ PlayRecordListWidget a;

    j(PlayRecordListWidget playRecordListWidget) {
        this.a = playRecordListWidget;
    }

    public final void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        if (!this.a.e) {
            if (this.a.f) {
                PlayRecordListWidget.v(this.a);
            } else {
                this.a.a(null);
            }
        }
    }

    public final void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
    }
}
