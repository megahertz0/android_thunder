package com.xunlei.tdlive.play.view;

import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: UserInfoWindowHelper.java
class ak implements ObjectCallBack {
    final /* synthetic */ aj a;

    ak(aj ajVar) {
        this.a = ajVar;
    }

    public void onResponse(int i, String str, Object obj) {
        ah.a(this.a.d, this.a.c, i, str, "\u7981\u8a00", true);
    }
}
