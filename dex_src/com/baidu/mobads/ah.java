package com.baidu.mobads;

import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

class ah implements IOAdEventListener {
    final /* synthetic */ SplashAd a;

    ah(SplashAd splashAd) {
        this.a = splashAd;
    }

    public void run(IOAdEvent iOAdEvent) {
        if (SplashAd.a(this.a) != null) {
            m.a().m().a(new ai(this, iOAdEvent));
        }
    }
}
