package com.xunlei.tdlive;

import com.qq.e.comm.constants.Constants.KEYS;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.user.f.b;

// compiled from: WebBrowserActivity.java
class fv implements b {
    final /* synthetic */ String a;
    final /* synthetic */ fu b;

    fv(fu fuVar, String str) {
        this.b = fuVar;
        this.a = str;
    }

    public void a(boolean z) {
        JsonWrapper jsonWrapper = new JsonWrapper("{}");
        jsonWrapper.putInt(KEYS.RET, z ? 0 : -1);
        this.b.a.callJS(this.a, jsonWrapper);
    }
}
