package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import org.json.JSONObject;

class b implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ a b;

    b(a aVar, IOAdEvent iOAdEvent) {
        this.b = aVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            this.b.a.d.onAdReady(this.b.a);
        } else if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            this.b.a.d.onAdSwitch();
            this.b.a.d.onAdShow(new JSONObject());
        } else if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            this.b.a.d.onAdFailed(m.a().q().getMessage(this.a.getData()));
        } else if ("AdUserClick".equals(this.a.getType())) {
            this.b.a.d.onAdClick(new JSONObject());
        } else if (IXAdEvent.AD_USER_CLOSE.equals(this.a.getType())) {
            m.a().m().a(this.b.a);
            this.b.a.d.onAdClose(new JSONObject());
        }
    }
}
