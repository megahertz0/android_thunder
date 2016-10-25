package com.baidu.mobads;

import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

class ak implements IOAdEventListener {
    final /* synthetic */ VideoAdView a;

    ak(VideoAdView videoAdView) {
        this.a = videoAdView;
    }

    public void run(IOAdEvent iOAdEvent) {
        m.a().m().a(new al(this, iOAdEvent));
    }
}
