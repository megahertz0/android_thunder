package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;

class ai implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ ah b;

    ai(ah ahVar, IOAdEvent iOAdEvent) {
        this.b = ahVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            m.a().f().i(this.a);
        } else if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            SplashAd.a(this.b.a).onAdPresent();
        } else if ("AdUserClick".equals(this.a.getType())) {
            SplashAd.a(this.b.a).onAdClick();
        } else if (IXAdEvent.AD_STOPPED.equals(this.a.getType())) {
            SplashAd.b(this.b.a).removeAllListeners();
            SplashAd.a(this.b.a).onAdDismissed();
        } else if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            SplashAd.b(this.b.a).removeAllListeners();
            SplashAd.a(this.b.a).onAdFailed(m.a().q().getMessage(this.a.getData()));
        }
    }
}
