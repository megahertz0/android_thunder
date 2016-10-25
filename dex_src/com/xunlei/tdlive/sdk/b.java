package com.xunlei.tdlive.sdk;

import android.os.Looper;
import com.xunlei.tdlive.a.c;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.protocol.XLLiveGetOnlineConfigRequest;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;

// compiled from: XLLiveSDK.java
class b implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ XLLiveSDK b;

    b(XLLiveSDK xLLiveSDK, String str) {
        this.b = xLLiveSDK;
        this.a = str;
    }

    public void run() {
        Looper.prepare();
        XLog.enableLog(false);
        ac.c(this.a);
        c.a(XLLiveSDK.a(this.b));
        new XLLiveGetOnlineConfigRequest().send(new c(this));
        BaseActivity.attachGlobalActivityEvents(new d(this));
    }
}
