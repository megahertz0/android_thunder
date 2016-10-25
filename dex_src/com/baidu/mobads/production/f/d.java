package com.baidu.mobads.production.f;

import android.view.ViewGroup;

class d implements Runnable {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public void run() {
        this.a.x.d("remote Interstitial.removeAd");
        try {
            if (this.a.e.getParent() != null) {
                ((ViewGroup) this.a.e.getParent()).removeView(this.a.e);
            }
            this.a.e.removeAllViews();
        } catch (Throwable e) {
            this.a.x.d("Interstitial.removeAd", e);
        }
    }
}
