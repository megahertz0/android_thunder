package com.baidu.mobads;

import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

class z implements IOAdEventListener {
    final /* synthetic */ InterstitialAd a;

    z(InterstitialAd interstitialAd) {
        this.a = interstitialAd;
    }

    public void run(IOAdEvent iOAdEvent) {
        m.a().f().i(InterstitialAd.TAG, new StringBuilder("evt.type=").append(iOAdEvent.getType()).toString());
        m.a().m().a(new aa(this, iOAdEvent));
    }
}
