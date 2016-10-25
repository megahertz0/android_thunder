package com.xunlei.tdlive;

import com.xunlei.tdlive.play.a.av.b;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class cc implements b {
    final /* synthetic */ au a;

    cc(au auVar) {
        this.a = auVar;
    }

    public void a() {
        XLog.d("LivePlayerDialog", "disConnectMic onBackPressed");
        this.a.onBackPressed();
    }
}
