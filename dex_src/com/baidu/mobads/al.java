package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.taobao.accs.common.Constants;

class al implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ ak b;

    al(ak akVar, IOAdEvent iOAdEvent) {
        this.b = akVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            this.b.a.c.onVideoPrepared();
        }
        if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            this.b.a.c.onVideoStart();
        }
        if (IXAdEvent.AD_CLICK_THRU.equals(this.a.getType())) {
            this.b.a.c.onVideoClickAd();
        }
        if (IXAdEvent.AD_STOPPED.equals(this.a.getType())) {
            this.b.a.c.onVideoFinish();
        }
        if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            this.a.getData().get(Constants.SHARED_MESSAGE_ID_FILE);
            this.b.a.c.onVideoError();
        }
    }
}
