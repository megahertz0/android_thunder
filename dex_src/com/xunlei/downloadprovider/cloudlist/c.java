package com.xunlei.downloadprovider.cloudlist;

import com.handmark.pulltorefresh.library.PullToRefreshBase.a;

// compiled from: CloudListBTFileActivity.java
final class c implements a {
    final /* synthetic */ CloudListBTFileActivity a;

    c(CloudListBTFileActivity cloudListBTFileActivity) {
        this.a = cloudListBTFileActivity;
    }

    public final void a() {
        if (!CloudListBTFileActivity.a(this.a)) {
            CloudListBTFileActivity.b(this.a).setRefreshing(true);
            CloudListBTFileActivity.c(this.a);
        }
    }
}
