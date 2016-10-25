package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: UserConnectMicPresenter.java
class aw implements ObjectCallBack {
    final /* synthetic */ av a;

    aw(av avVar) {
        this.a = avVar;
    }

    public void onResponse(int i, String str, Object obj) {
        this.a.d(i, str);
    }
}
