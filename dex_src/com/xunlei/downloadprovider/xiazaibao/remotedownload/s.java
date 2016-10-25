package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.xiazaibao.view.LoadingMoreRecyclerView;
import com.xunlei.xiazaibao.sdk.XZBDownloadTaskSet;

// compiled from: RemoteDownloadListFragment.java
final class s implements e {
    final /* synthetic */ RemoteDownloadListFragment a;

    s(RemoteDownloadListFragment remoteDownloadListFragment) {
        this.a = remoteDownloadListFragment;
    }

    public final void b(int i) {
        RemoteDownloadListFragment.a(this.a).set(false);
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.g(this.a);
        }
    }

    public final void a(int i, XZBDownloadTaskSet xZBDownloadTaskSet) {
        RemoteDownloadListFragment.a(this.a).set(false);
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.g(this.a);
            RemoteDownloadListFragment.a(this.a, 0, xZBDownloadTaskSet);
        }
    }

    public final void a() {
        RemoteDownloadListFragment.h(this.a);
    }

    public final void b() {
        RemoteDownloadListFragment.a(this.a).set(true);
    }

    public final void c() {
        RemoteDownloadListFragment.i(this.a).set(true);
    }

    public final void a(int i, int i2, String str) {
        new StringBuilder("onFirstFail cookie = ").append(i).append(" errorCode = ").append(i2).append(" errorMsg = ").append(str);
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.a(this.a).set(false);
            RemoteDownloadListFragment.b(this.a, i2);
            RemoteDownloadListFragment.g(this.a);
            RemoteDownloadListFragment.a(this.a, i2, null);
        }
    }

    public final void a(int i, XZBDownloadTaskSet xZBDownloadTaskSet, boolean z) {
        new StringBuilder("onFirstSuccess cookie = ").append(i).append(" hasMore? = ").append(z);
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.a(this.a).set(false);
            RemoteDownloadListFragment.j(this.a).a(xZBDownloadTaskSet.getTasks(), z);
            RemoteDownloadListFragment.j(this.a).b();
            RemoteDownloadListFragment.k(this.a).a(z);
            RemoteDownloadListFragment.g(this.a);
            RemoteDownloadListFragment.a(this.a, 0, xZBDownloadTaskSet);
        }
    }

    public final void b(int i, int i2, String str) {
        new StringBuilder("onMoreFail cookie = ").append(i).append(" errorCode = ").append(i2).append(" errorMsg = ").append(str);
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.a(this.a).set(false);
            RemoteDownloadListFragment.i(this.a).set(false);
            LoadingMoreRecyclerView k = RemoteDownloadListFragment.k(this.a);
            if (k.a && k.c <= k.d - 1) {
                k.scrollBy(0, -k.getChildAt(k.getChildCount() - 1).getHeight());
            }
            k.b = false;
            RemoteDownloadListFragment.a(this.a, i2, null);
        }
    }

    public final void b(int i, XZBDownloadTaskSet xZBDownloadTaskSet, boolean z) {
        new StringBuilder("onMoreSuccess cookie = ").append(i).append(" hasMore? = ").append(z);
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.a(this.a).set(false);
            RemoteDownloadListFragment.i(this.a).set(false);
            RemoteDownloadListFragment.j(this.a).b(xZBDownloadTaskSet.getTasks(), z);
            RemoteDownloadListFragment.j(this.a).b();
            RemoteDownloadListFragment.k(this.a).a(z);
        }
    }

    public final void c(int i, int i2, String str) {
        new StringBuilder("onRefreshFail cookie = ").append(i).append(" errorCode = ").append(i2).append(" errorMsg = ").append(str);
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.a(this.a).set(false);
            RemoteDownloadListFragment.g(this.a);
            if (RemoteDownloadListFragment.d(this.a) != 2) {
                RemoteDownloadListFragment.a(this.a, i2, null);
            }
        }
    }

    public final void c(int i, XZBDownloadTaskSet xZBDownloadTaskSet, boolean z) {
        new StringBuilder("onRefreshSuccess cookie = ").append(i).append(" hasMore? = ").append(z);
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.a(this.a).set(false);
            RemoteDownloadListFragment.j(this.a).a(xZBDownloadTaskSet.getTasks(), z);
            RemoteDownloadListFragment.j(this.a).b();
            RemoteDownloadListFragment.g(this.a);
            if (RemoteDownloadListFragment.d(this.a) != 2) {
                RemoteDownloadListFragment.a(this.a, 0, xZBDownloadTaskSet);
            }
        }
    }

    public final void a(int i) {
        if (RemoteDownloadListFragment.a(this.a, i)) {
            RemoteDownloadListFragment.a(this.a, RemoteDownloadListFragment.j(this.a).getItemCount() > 0);
        }
    }
}
