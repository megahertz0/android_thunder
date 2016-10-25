package com.xunlei.downloadprovider.cloudlist;

import com.xunlei.downloadprovider.cloudlist.a.b;

// compiled from: CloudListBTFileActivity.java
final class d implements b {
    final /* synthetic */ CloudListBTFileActivity a;

    d(CloudListBTFileActivity cloudListBTFileActivity) {
        this.a = cloudListBTFileActivity;
    }

    public final void a(int i, Object obj, boolean z) {
        if (obj == CloudListBTFileActivity.d(this.a)) {
            CloudListBTFileActivity.e(this.a);
            if (i == 0) {
                CloudListBTFileActivity.f(this.a).b();
                CloudListBTFileActivity.g(this.a).notifyDataSetChanged();
                CloudListBTFileActivity.b(this.a).m();
                if (z) {
                    CloudListBTFileActivity.h(this.a);
                    return;
                }
                return;
            }
            CloudListBTFileActivity.i(this.a);
        }
    }
}
