package com.xunlei.tdlive.control;

// compiled from: HorizontialListView.java
class k implements Runnable {
    final /* synthetic */ HorizontialListView a;

    k(HorizontialListView horizontialListView) {
        this.a = horizontialListView;
    }

    public void run() {
        this.a.requestLayout();
    }
}
