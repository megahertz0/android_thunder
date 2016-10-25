package com.xunlei.tdlive.play.view;

import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: UserInfoWindowHelper.java
class au implements ObjectCallBack {
    final /* synthetic */ at a;

    au(at atVar) {
        this.a = atVar;
    }

    public void onResponse(int i, String str, Object obj) {
        ah.a(this.a.d, this.a.c, i, str, "\u89e3\u7981", false);
    }
}
