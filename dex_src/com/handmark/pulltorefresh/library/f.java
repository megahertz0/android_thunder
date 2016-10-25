package com.handmark.pulltorefresh.library;

// compiled from: PullToRefreshBase.java
final class f implements Runnable {
    final /* synthetic */ PullToRefreshBase a;

    f(PullToRefreshBase pullToRefreshBase) {
        this.a = pullToRefreshBase;
    }

    public final void run() {
        this.a.requestLayout();
    }
}
