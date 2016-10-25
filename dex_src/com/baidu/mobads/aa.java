package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;

class aa implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ z b;

    aa(z zVar, IOAdEvent iOAdEvent) {
        this.b = zVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            this.b.a.e.onAdReady();
        } else if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            this.b.a.e.onAdFailed(m.a().q().getMessage(this.a.getData()));
        } else if (IXAdEvent.AD_STOPPED.equals(this.a.getType())) {
            this.b.a.e.onAdDismissed();
        } else if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            this.b.a.e.onAdPresent();
        } else if ("AdUserClick".equals(this.a.getType())) {
            this.b.a.e.onAdClick(this.b.a);
        }
    }
}
