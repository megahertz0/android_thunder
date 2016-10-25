package com.baidu.mobads.production.h;

import com.baidu.mobads.f.a;
import com.baidu.mobads.interfaces.event.IXAdEvent;

class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        this.a.dispatchEvent(new a(IXAdEvent.AD_LOADED));
    }
}
