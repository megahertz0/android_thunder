package com.xunlei.downloadprovider.vod;

// compiled from: VodPlayerFirstLoadingView.java
final class al implements Runnable {
    final /* synthetic */ VodPlayerFirstLoadingView a;

    al(VodPlayerFirstLoadingView vodPlayerFirstLoadingView) {
        this.a = vodPlayerFirstLoadingView;
    }

    public final void run() {
        VodPlayerFirstLoadingView.a(this.a, VodPlayerFirstLoadingView.i);
        this.a.setPercent(this.a.k);
        if (this.a.k < this.a.j) {
            this.a.postDelayed(this.a.l, VodPlayerFirstLoadingView.h);
        }
        if (this.a.k >= 100) {
            this.a.removeCallbacks(this.a.l);
            this.a.f;
        }
    }
}
