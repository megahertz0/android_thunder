package com.baidu.mobads.production;

import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.openad.interfaces.utils.IOAdTimer.EventHandler;
import com.umeng.a;

class i implements EventHandler {
    final /* synthetic */ a a;

    i(a aVar) {
        this.a = aVar;
    }

    public void onTimerComplete() {
        this.a.h();
        this.a.g();
        this.a.a(XAdErrorCode.REQUEST_TIMEOUT, a.d);
    }

    public void onTimer(int i) {
    }
}
