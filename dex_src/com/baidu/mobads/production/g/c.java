package com.baidu.mobads.production.g;

import android.view.ViewGroup;

class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void run() {
        this.a.x.d("remote Interstitial.removeAd");
        b.a(this.a, false);
        try {
            b.a(this.a).removeAllViews();
            ViewGroup a = b.a(this.a, b.b(this.a).getContext());
            b.c(this.a).removeAllViews();
            a.removeView(b.c(this.a));
        } catch (Throwable e) {
            this.a.x.d("Interstitial.removeAd", e);
        }
    }
}
