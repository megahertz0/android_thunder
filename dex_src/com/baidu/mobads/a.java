package com.baidu.mobads;

import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

class a implements IOAdEventListener {
    final /* synthetic */ AdView a;

    a(AdView adView) {
        this.a = adView;
    }

    public void run(IOAdEvent iOAdEvent) {
        if (this.a.d != null) {
            m.a().m().a(new b(this, iOAdEvent));
        }
    }
}
