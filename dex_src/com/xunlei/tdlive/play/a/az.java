package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.play.view.ConnectMicView.a;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;

// compiled from: UserConnectMicPresenter.java
class az implements JsonCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ av b;

    az(av avVar, int i) {
        this.b = avVar;
        this.a = i;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i != 0) {
            this.b.e("\u7f51\u7edc\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
            return;
        }
        this.b.a(a.a);
        if (this.a == 0) {
            this.b.f();
        }
    }
}
