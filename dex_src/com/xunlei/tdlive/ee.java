package com.xunlei.tdlive;

// compiled from: SDKLiveListFragment.java
class ee implements Runnable {
    final /* synthetic */ ea a;

    ee(ea eaVar) {
        this.a = eaVar;
    }

    public void run() {
        ea.b(this.a).m();
        ea.a(this.a, "down_refresh");
    }
}
